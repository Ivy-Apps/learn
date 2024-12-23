package ui.screen.lesson.composable.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.ScreenType.Mobile
import component.screenType
import ui.screen.lesson.CodeItemViewState
import ui.screen.lesson.composable.ItemSpacingMedium
import ui.theme.Gray
import ui.theme.colorsExt

@Composable
fun CodeLessonItem(
  viewState: CodeItemViewState,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.padding(top = ItemSpacingMedium),
    shape = RoundedCornerShape(12.dp),
    backgroundColor = MaterialTheme.colorsExt.backgroundVariant,
    contentColor = MaterialTheme.colorsExt.onBackgroundVariant,
    border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
  ) {
    Text(
      modifier = modifier
        .padding(
          vertical = 24.dp,
          horizontal = when (screenType()) {
            Mobile -> 8.dp
            else -> 24.dp
          }
        )
        .then(
          when (screenType()) {
            Mobile -> Modifier.sizeIn(
              minWidth = 500.dp,
            )

            else -> Modifier
          }
        ),
      text = highlightSyntax(viewState.code),
      style = MaterialTheme.typography.body1.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp,
      ),
      textAlign = TextAlign.Start,
    )
  }
}

@Composable
fun highlightSyntax(code: String): AnnotatedString {
  val keywords = setOf("def", "if", "elif", "return")
  val primaryColor = MaterialTheme.colors.primary
  val commentColor = Gray

  return buildAnnotatedString {
    val buffer = StringBuilder()
    val commentBuffer = StringBuilder()
    var i = 0
    var inComment = false

    while (i < code.length) {
      val char = code[i]

      if (inComment) {
        // Append the entire comment in gray
        commentBuffer.append(char)
        i++
        if (char == '\n') {
          withStyle(style = SpanStyle(color = commentColor)) {
            append(commentBuffer.toString())
          }
          commentBuffer.clear()
          inComment = false
          continue
        } else {
          continue
        }
      }

      when {
        char == '#' -> {
          // Process existing buffer before starting a comment
          val word = buffer.toString()
          if (keywords.contains(word)) {
            withStyle(style = SpanStyle(color = primaryColor)) {
              append(word)
            }
          } else {
            append(word)
          }
          buffer.clear()

          // Start processing the comment
          commentBuffer.append(char)
          inComment = true
        }

        char.isLetterOrDigit() || char == '_' -> {
          // Accumulate potential keyword
          buffer.append(char)
        }

        else -> {
          // Process accumulated word
          val word = buffer.toString()
          if (keywords.contains(word)) {
            withStyle(style = SpanStyle(color = primaryColor)) {
              append(word)
            }
          } else {
            append(word)
          }
          buffer.clear()

          // Append the non-word character
          append(char.toString())
        }
      }

      i++
    }

    // Handle any remaining keyword in the buffer
    val word = buffer.toString()
    if (keywords.contains(word)) {
      withStyle(style = SpanStyle(color = primaryColor)) {
        append(word)
      }
    } else {
      append(word)
    }
  }
}