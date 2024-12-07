package ui.screen.lesson

import androidx.compose.runtime.*
import arrow.core.Either
import arrow.optics.optics
import data.lesson.LessonRepository
import domain.analytics.Analytics
import domain.analytics.Param
import domain.analytics.Source
import domain.model.LessonProgress
import domain.model.LessonWithProgress
import ivy.model.*
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.ComposeViewModel
import ui.EventHandler
import ui.VmContext
import ui.screen.lesson.mapper.LessonViewStateMapper
import util.Logger


class LessonViewModel(
    private val courseId: CourseId,
    private val lessonId: LessonId,
    private val lessonName: String,
    override val screenScope: CoroutineScope,
    private val repository: LessonRepository,
    private val viewStateMapper: LessonViewStateMapper,
    private val eventHandlers: Set<EventHandler<*, LocalState>>,
    private val analytics: Analytics,
    private val logger: Logger,
) : ComposeViewModel<LessonViewState, LessonViewEvent>, LessonVmContext {

    private var lessonResponse by mutableStateOf<Either<String, LessonWithProgress>?>(null)
    private var localState by mutableStateOf(LocalState.Initial)

    override val state: LocalState
        get() = localState

    override fun modifyState(transformation: (LocalState) -> LocalState) {
        localState = transformation(localState)
        saveLessonProgress(localState)
    }

    private fun saveLessonProgress(localState: LocalState) {
        screenScope.launch {
            repository.saveLessonProgress(
                course = courseId,
                lesson = lessonId,
                progress = LessonProgress(
                    selectedAnswers = localState.selectedAnswers,
                    openAnswersInput = localState.openAnswersInput,
                    chosen = localState.chosen,
                    answered = localState.answered,
                    completed = localState.completed,
                )
            ).onLeft { errMsg ->
                logger.error("Lesson progress: $errMsg")
            }
        }
    }

    @Composable
    override fun viewState(): LessonViewState {
        LaunchedEffect(Unit) {
            loadLesson()
            analytics.logEvent(
                source = Source.Lesson,
                event = "view",
                params = mapOf(
                    Param.CourseId to courseId.value,
                    Param.LessonId to lessonId.value,
                    Param.LessonName to lessonName,
                )
            )
        }
        return when (val response = lessonResponse) {
            is Either.Right -> remember(localState, lessonResponse) {
                with(viewStateMapper) {
                    response.value.lesson.toViewState(localState)
                }
            }

            null, is Either.Left -> LessonViewState(
                title = lessonName,
                items = persistentListOf(),
                cta = null,
                progress = LessonProgressViewState(0, 1),
                itemsLoadedDiff = 0,
            )
        }
    }

    private suspend fun loadLesson() {
        lessonResponse = repository.fetchLesson(
            course = courseId,
            lesson = lessonId
        ).onRight {
            localState = LocalState(
                selectedAnswers = it.progress.selectedAnswers,
                openAnswersInput = it.progress.openAnswersInput,
                chosen = it.progress.chosen,
                answered = it.progress.answered,
                completed = it.progress.completed,
            )
        }
    }

    override fun onEvent(event: LessonViewEvent) {
        val eventHandler = eventHandlers.firstOrNull { handler ->
            event::class in handler.eventTypes
        }
        checkNotNull(eventHandler) { "EventHandler for ${event::class} is not defined!" }

        screenScope.launch {
            @Suppress("UNCHECKED_CAST")
            val typedEventHandler = eventHandler as EventHandler<LessonViewEvent, LocalState>
            with(typedEventHandler) { handleEvent(event) }
        }
    }

    @optics
    data class LocalState(
        val selectedAnswers: Map<LessonItemId, Set<AnswerId>>,
        val openAnswersInput: Map<LessonItemId, String>,
        val chosen: Map<LessonItemId, ChoiceOptionId>,
        val answered: Set<LessonItemId>,
        val completed: Set<LessonItemId>,
    ) {
        companion object {
            val Initial = LocalState(
                selectedAnswers = emptyMap(),
                openAnswersInput = emptyMap(),
                chosen = emptyMap(),
                answered = emptySet(),
                completed = emptySet(),
            )
        }
    }
}

typealias LessonVmContext = VmContext<LessonViewModel.LocalState>