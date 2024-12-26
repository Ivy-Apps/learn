package ui.screen.course.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.ScreenType.*
import component.screenType
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
    val horizontalPadding = when (screenType()) {
      Mobile -> 8.dp
      Tablet -> 16.dp
      Desktop -> 16.dp
    }
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
      itemsIndexed(
        items = viewState.items,
        key = { index, item -> "${index}_$item" }
      ) { _, item ->
        when (item) {
          is CourseItemViewState.Arrow -> LessonArrow(
            progress = item.progress
          )

          is CourseItemViewState.Lesson -> LessonCard(
            lesson = item,
            onLessonClick = {
              onEvent(CourseViewEvent.OnLessonClick(item))
            }
          )
        }
      }
    }
  }
}