package ui.screen.course.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import ui.screen.course.CourseItemViewState
import ui.screen.course.CourseViewEvent
import ui.screen.course.CourseViewState

@Composable
fun CourseContent(
    viewState: CourseViewState,
    onEvent: (CourseViewEvent) -> Unit
) {
    LearnScaffold(
        backButton = BackButton(onBackClick = {
            onEvent(CourseViewEvent.OnBackClick)
        }),
        title = viewState.name
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 20.dp, bottom = 48.dp)
        ) {
            items(viewState.items) {
                when (it) {
                    is CourseItemViewState.Arrow -> LessonArrow(
                        progress = it.progress
                    )

                    is CourseItemViewState.Lesson -> LessonCard(
                        lesson = it,
                        onLessonClick = {
                            onEvent(CourseViewEvent.OnLessonClick(it))
                        }
                    )
                }
            }
        }
    }
}