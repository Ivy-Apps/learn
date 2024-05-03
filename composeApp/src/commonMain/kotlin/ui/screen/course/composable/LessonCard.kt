package ui.screen.course.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        border = BorderStroke(
            width = 4.dp,
            color = when (lesson.progress) {
                ProgressViewState.Completed -> Green
                ProgressViewState.ToDo -> MaterialTheme.colors.secondary
                ProgressViewState.Upcoming -> Gray
            }
        ),
        onClick = onLessonClick
    ) {
        Column {
            Spacer(Modifier.height(12.dp))
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
            Spacer(Modifier.height(12.dp))
        }
    }
}