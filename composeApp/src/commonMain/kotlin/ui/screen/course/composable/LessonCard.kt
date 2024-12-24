package ui.screen.course.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import component.IvyImage
import component.ScreenType.*
import component.isLargeScreen
import component.screenType
import component.text.SubTitle
import component.text.Title
import ui.screen.course.CourseItemViewState
import ui.screen.course.ProgressViewState
import ui.theme.Gray
import ui.theme.Green

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LessonCard(
  lesson: CourseItemViewState.Lesson,
  modifier: Modifier = Modifier,
  onLessonClick: () -> Unit
) {
  Card(
    modifier = modifier
      .sizeIn(maxWidth = 600.dp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(24.dp),
    elevation = 4.dp,
    border = BorderStroke(
      width = 2.dp,
      color = when (lesson.progress) {
        ProgressViewState.Completed -> Green
        ProgressViewState.Current -> MaterialTheme.colors.primary
        ProgressViewState.Upcoming -> Gray
      }
    ),
    onClick = onLessonClick
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      var height by remember { mutableStateOf(132.dp) }
      LessonImage(
        modifier = Modifier.height(height),
        imageUrl = lesson.imageUrl
      )
      val density = LocalDensity.current
      Column(
        modifier = Modifier
          .onSizeChanged {
            height = maxOf(with(density) { it.height.toDp() }, height)
          }
          .defaultMinSize(minHeight = height)
          .padding(
            start = if (isLargeScreen()) 24.dp else 16.dp,
            end = if (isLargeScreen()) 16.dp else 12.dp
          )
          .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
      ) {
        Title(
          text = lesson.name,
          fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        SubTitle(
          text = lesson.tagline,
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
        )
      }
    }
  }
}

@Composable
private fun LessonImage(
  imageUrl: String,
  modifier: Modifier = Modifier,
) {
  IvyImage(
    imageUrl = imageUrl,
    modifier = modifier
      .aspectRatio(
        when (screenType()) {
          Mobile -> 0.8f
          Tablet -> 1f
          Desktop -> 1f
        }
      )
      .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)),
    contentScale = ContentScale.FillHeight,
    contentAlignment = Alignment.CenterStart
  )
}