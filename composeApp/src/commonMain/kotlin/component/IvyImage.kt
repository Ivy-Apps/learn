package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun IvyImage(
  imageUrl: String,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Fit,
  contentAlignment: Alignment = Alignment.Center,
) {
  KamelImage(
    resource = { asyncPainterResource(imageUrl) },
    contentDescription = null,
    modifier = modifier,
    contentScale = contentScale,
    contentAlignment = contentAlignment,
    onFailure = {
      ImagePlaceHolder(loading = false)
    },
    onLoading = {
      ImagePlaceHolder(loading = true)
    }
  )
}

@Composable
fun BoxScope.ImagePlaceHolder(
  loading: Boolean,
) {
  Spacer(
    Modifier
      .fillMaxSize()
      .background(
        MaterialTheme.colors.primary.copy(alpha = 0.3f)
      )
  )
  if (loading) {
    CircularProgressIndicator(
      modifier = Modifier.align(Alignment.Center)
    )
  }
}