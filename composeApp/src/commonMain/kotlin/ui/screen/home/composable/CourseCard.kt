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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.button.ContinueButton
import component.text.SubTitle
import component.text.Title
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.screen.home.HomeItemViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseCard(
    course: HomeItemViewState.Course,
    modifier: Modifier = Modifier,
    onCourseClick: () -> Unit
) {
    Card(
        modifier = modifier.sizeIn(maxWidth = 500.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        onClick = onCourseClick
    ) {
        Column {
            Box {
                KamelImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillWidth,
                    resource = asyncPainterResource(course.imageUrl),
                    contentDescription = null
                )
                ContinueButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = onCourseClick
                )
            }
            Spacer(Modifier.height(12.dp))
            Title(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = course.name,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            SubTitle(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = course.tagline
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}