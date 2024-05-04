package ui.screen.course.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            color = if (lesson.progress == ProgressViewState.Current) {
                MaterialTheme.colors.primary
            } else {
                Gray
            }
        ),
        onClick = onLessonClick
    ) {
        Row {
            val height = 132.dp
            LessonImage(
                modifier = Modifier.height(height),
                imageUrl = lesson.imageUrl
            )
            Column(
                modifier = Modifier.height(height),
                verticalArrangement = Arrangement.Center,
            ) {
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
            .aspectRatio(0.8f),
        contentScale = ContentScale.FillHeight,
        contentAlignment = Alignment.CenterStart,
        resource = asyncPainterResource(imageUrl),
        contentDescription = null
    )
}