package ui.screen.course.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.text.SubTitle
import component.text.Title
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
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
        modifier = modifier.sizeIn(maxWidth = 500.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        border = BorderStroke(
            width = 2.dp,
            color = progressToColor(lesson.progress)
        ),
        onClick = onLessonClick
    ) {
        Row {
            KamelImage(
                modifier = Modifier
                    .width(160.dp)
                    .height(160.dp),
                contentScale = ContentScale.FillBounds,
                contentAlignment = Alignment.CenterStart,
                resource = asyncPainterResource(lesson.imageUrl),
                contentDescription = null
            )

            Column {
                Spacer(Modifier.height(24.dp))
                Title(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = lesson.name,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                SubTitle(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = lesson.tagLine
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun progressToColor(progress: ProgressViewState): Color {
    return when (progress) {
        ProgressViewState.Completed -> Green
        ProgressViewState.ToDo -> MaterialTheme.colors.secondary
        ProgressViewState.Upcoming -> Gray
    }
}