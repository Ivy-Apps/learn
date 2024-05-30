package ui.screen.lesson

import androidx.compose.runtime.*
import arrow.core.Either
import arrow.optics.optics
import data.LessonRepository
import ivy.model.*
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.ComposeViewModel
import ui.EventHandler
import ui.VmContext
import ui.screen.lesson.mapper.LessonViewStateMapper


class LessonViewModel(
    private val courseId: CourseId,
    private val lessonId: LessonId,
    private val lessonName: String,
    override val screenScope: CoroutineScope,
    private val repository: LessonRepository,
    private val viewStateMapper: LessonViewStateMapper,
    private val eventHandlers: Set<EventHandler<*, LocalState>>
) : ComposeViewModel<LessonViewState, LessonViewEvent>, LessonVmContext {

    private var lessonResponse by mutableStateOf<Either<String, Lesson>?>(null)
    private var localState by mutableStateOf(LocalState.Initial)

    override val state: LocalState
        get() = localState

    override fun modifyState(transformation: (LocalState) -> LocalState) {
        localState = transformation(localState)
    }

    @Composable
    override fun viewState(): LessonViewState {
        LaunchedEffect(Unit) {
            lessonResponse = repository.fetchLesson(
                course = courseId,
                lesson = lessonId
            )
        }
        return when (val response = lessonResponse) {
            is Either.Right -> remember(localState, lessonResponse) {
                with(viewStateMapper) {
                    response.value.toViewState(localState)
                }
            }

            null, is Either.Left -> LessonViewState(
                title = lessonName,
                items = persistentListOf(),
                cta = null,
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
        val answers: Map<LessonItemId, Set<AnswerId>>,
        val openAnswers: Map<LessonItemId, String>,
        val completed: Set<LessonItemId>,
        val choices: Map<LessonItemId, ChoiceOptionId>,
    ) {
        companion object {
            val Initial = LocalState(
                answers = emptyMap(),
                openAnswers = emptyMap(),
                choices = emptyMap(),
                completed = emptySet(),
            )
        }
    }
}

typealias LessonVmContext = VmContext<LessonViewModel.LocalState>