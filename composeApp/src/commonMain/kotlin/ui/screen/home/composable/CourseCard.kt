package ui.screen.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import ui.screen.home.HomeItemViewState
import ui.theme.LightGray

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
            Banner(
                imageUrl = course.imageUrl,
                onClick = onCourseClick
            )
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

@Composable
private fun Banner(
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        KamelImage(
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp),
            contentScale = ContentScale.FillWidth,
            resource = asyncPainterResource(imageUrl),
            contentDescription = null
        )
        ContinueButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onClick
        )
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
                modifier = Modifier.size(32.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = LightGray
            )
        }
    )
}