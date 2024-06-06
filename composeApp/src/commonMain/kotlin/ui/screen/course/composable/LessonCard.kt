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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.isLargeScreen
import component.text.SubTitle
import component.text.Title
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.screen.course.CourseItemViewState
import ui.screen.course.ProgressViewState
import ui.theme.Gray

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
            color = if (lesson.progress == ProgressViewState.Current) {
                MaterialTheme.colors.primary
            } else {
                Gray
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
                        height = minOf(with(density) { it.height.toDp() }, height)
                    }
                    .defaultMinSize(minHeight = height)
                    .padding(
                        start = if (isLargeScreen()) 24.dp else 20.dp,
                        end = if (isLargeScreen()) 16.dp else 12.dp
                    )
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Title(
                    text = lesson.name,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                SubTitle(text = lesson.tagline)
            }
        }
    }
}

@Composable
private fun LessonImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    KamelImage(
        modifier = modifier
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
        contentAlignment = Alignment.CenterStart,
        resource = asyncPainterResource(imageUrl),
        contentDescription = null
    )
}