package ui.screen.home.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import component.text.SubTitle
import component.text.Title
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ivy.model.CourseId
import ui.screen.home.HomeItemViewState
import ui.screen.home.HomeViewEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseCard(
    course: HomeItemViewState.Course,
    modifier: Modifier = Modifier,
    onEvent: (HomeViewEvent) -> Unit
) {
    Card(
        modifier = modifier.sizeIn(maxWidth = 500.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        onClick = {
            onEvent(HomeViewEvent.OnCourseClick(CourseId(course.id)))
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Box {
                KamelImage(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    resource = asyncPainterResource(course.imageUrl),
                    contentDescription = null
                )
                ContinueButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = {
                        onEvent(HomeViewEvent.OnCourseClick(CourseId(course.id)))
                    })
            }
            Spacer(Modifier.height(12.dp))
            Title(course.name)
            Spacer(Modifier.height(8.dp))
            SubTitle(course.tagLine)
        }
    }
}

@Composable
private fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        }
    )
}