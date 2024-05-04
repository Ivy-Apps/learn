package ui.screen.lesson.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.platformHorizontalPadding
import ivy.model.ChoiceItem
import ivy.model.ImageItem
import ivy.model.LessonNavigationItem
import ivy.model.LinkItem
import ivy.model.LottieAnimationItem
import ivy.model.MysteryItem
import ivy.model.OpenQuestionItem
import ivy.model.QuestionItem
import ivy.model.TextContentItem
import ui.screen.lesson.LessonViewEvent
import ui.screen.lesson.LessonViewState

@Composable
fun LessonContent(
    viewState: LessonViewState,
    onEvent: (LessonViewEvent) -> Unit
) {
    LearnScaffold(
        backButton = BackButton(onBackClick = {
            onEvent(LessonViewEvent.OnBackClick)
        }),
        title = viewState.lesson.name
    ) { contentPadding ->
        val horizontalPadding = platformHorizontalPadding()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                top = 32.dp,
                bottom = 48.dp,
                start = horizontalPadding,
                end = horizontalPadding,
            )
        ) {
            item(key = "lesson") {
                val lesson = viewState.lesson.content.items.entries.first().value
                when (lesson) {
                    is ChoiceItem -> TODO()
                    is ImageItem -> TODO()
                    is LessonNavigationItem -> TODO()
                    is LinkItem -> TODO()
                    is LottieAnimationItem -> TODO()
                    is MysteryItem -> TODO()
                    is OpenQuestionItem -> TODO()
                    is QuestionItem -> TODO()
                    is TextContentItem -> {
                        Text(lesson.text)
                    }
                }
            }
        }
    }
}