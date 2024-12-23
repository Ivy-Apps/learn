package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import component.IvyImage
import component.ScreenType.*
import component.screenType
import ui.screen.lesson.ImageItemViewState
import ui.screen.lesson.composable.ItemSpacingMedium

@Composable
fun imageSize(): Dp = when (screenType()) {
  Mobile, Tablet -> 264.dp
  Desktop -> 320.dp
}

@Composable
fun ImageLessonItem(
  viewState: ImageItemViewState,
  modifier: Modifier = Modifier,
) {
  IvyImage(
    modifier = modifier
      .padding(top = ItemSpacingMedium)
      .size(imageSize())
      .clip(RoundedCornerShape(16.dp)),
    imageUrl = viewState.imageUrl,
  )
}