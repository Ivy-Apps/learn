package ui.screen.intro.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import ivy.IvyUrls
import ui.theme.IvyTheme

@Composable
fun IntroLegalText(
  modifier: Modifier = Modifier,
) {
  val uriHandler = LocalUriHandler.current
  val text = buildAnnotatedString {
    withStyle(SpanStyle(MaterialTheme.colors.onBackground)) {
      append("By continuing, you agree to our ")
    }
    pushStringAnnotation(
      tag = "URL",
      annotation = IvyUrls.tos
    )
    withStyle(
      style = SpanStyle(
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colors.secondary,
      )
    ) {
      append("Terms of Service")
    }
    pop()
    withStyle(SpanStyle(MaterialTheme.colors.onBackground)) {
      append(" and ")
    }
    pushStringAnnotation(
      tag = "URL",
      annotation = IvyUrls.privacy
    )
    withStyle(
      style = SpanStyle(
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colors.secondary,
      )
    ) {
      append("Privacy Policy")
    }
    pop()
    withStyle(SpanStyle(MaterialTheme.colors.onBackground)) {
      append(".")
    }
  }
  ClickableText(
    modifier = modifier,
    text = text,
    style = IvyTheme.typography.caption.copy(
      textAlign = TextAlign.Center,
      // TODO: There's a Compose Multiplatform bug so better use default font
      fontFamily = null,
    ),
    onClick = { offset ->
      val annotation = text.getStringAnnotations(tag = "URL", start = offset, end = offset)
        .firstOrNull()
      annotation?.let {
        val url = it.item
        uriHandler.openUri(url)
      }
    },
  )
}