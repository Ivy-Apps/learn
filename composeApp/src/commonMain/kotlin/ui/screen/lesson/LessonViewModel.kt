package ui.screen.lesson

import androidx.compose.runtime.Composable
import ivy.model.ImageUrl
import ivy.model.Lesson
import ivy.model.LessonContent
import ivy.model.LessonId
import ivy.model.LessonItemId
import ivy.model.TextContentItem
import ivy.model.TextContentStyle
import ui.ComposeViewModel
import ui.navigation.Navigation

class LessonViewModel(
    private val navigation: Navigation
) : ComposeViewModel<LessonViewState, LessonViewEvent> {
    @Composable
    override fun viewState(): LessonViewState {
        return LessonViewState(
            lesson = Lesson(
                id = LessonId(value = "123"),
                name = "Lesson 1",
                tagline = "Learn programming by thinking...",
                image = ImageUrl(
                    url = "https://cdn.pixabay.com/photo/2024/04/23/11/55/building-" +
                            "8714863_1280.jpg"
                ),
                content = LessonContent(
                    rootItem = LessonItemId(value = "324"),
                    items = mapOf(
                        LessonItemId("4") to TextContentItem(
                            id = LessonItemId(value = "5"),
                            text = "What are ADTs - ADTs or algebraic data types are...",
                            style = TextContentStyle.Body,
                            next = null
                        )
                    )
                )
            )
        )
    }

    override fun onEvent(event: LessonViewEvent) {
        when (event) {
            LessonViewEvent.OnBackClick -> TODO()
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }
}