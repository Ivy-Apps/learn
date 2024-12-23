package ui.screen.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.IvyImage
import component.ScreenType.*
import component.isLargeScreen
import component.screenType
import component.text.SubTitle
import component.text.Title
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
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = course.progress,
            )
            val isLargeScreen = isLargeScreen()
            Spacer(Modifier.height(if (isLargeScreen) 24.dp else 16.dp))
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
            Spacer(Modifier.height(if (isLargeScreen) 32.dp else 16.dp))
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
        IvyImage(
            imageUrl = imageUrl,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(
                    when (screenType()) {
                        Mobile -> 3.5f
                        Tablet -> 4f
                        Desktop -> 5f
                    }
                ),
            contentScale = ContentScale.Crop
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
