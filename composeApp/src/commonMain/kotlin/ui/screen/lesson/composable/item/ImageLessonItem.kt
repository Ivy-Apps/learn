package ui.screen.lesson.composable.item

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.screen.lesson.ImageItemViewState
import ui.screen.lesson.composable.ItemSpacing

@Composable
fun ImageLessonItem(
    viewState: ImageItemViewState,
    modifier: Modifier = Modifier,
) {
    KamelImage(
        modifier = modifier.padding(top = ItemSpacing)
            .clip(RoundedCornerShape(16.dp)),
        resource = asyncPainterResource(viewState.imageUrl),
        contentDescription = null
    )
}