package component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import kotlinx.collections.immutable.ImmutableMap
import ui.theme.Gray

@Composable
fun highlightSyntax(
  code: String,
  syntaxHighlight: SyntaxHighlightProvider,
): AnnotatedString {
  val keywords = syntaxHighlight.rememberKeywords()
  val commentColor = Gray

  return remember(code, syntaxHighlight) {
    buildAnnotatedString {
      val buffer = StringBuilder()
      val commentBuffer = StringBuilder()
      var i = 0
      var inComment = false

      fun processBufferedKeyword() {
        val word = buffer.toString()
        if (word in keywords) {
          withStyle(style = SpanStyle(color = keywords[word]!!)) {
            append(word)
          }
        } else {
          append(word)
        }
        buffer.clear()
      }

      while (i < code.length) {
        val char = code[i]

        if (inComment) {
          // Append the entire comment in gray
          commentBuffer.append(char)
          if (char == '\n') {
            withStyle(style = SpanStyle(color = commentColor)) {
              append(commentBuffer.toString())
            }
            commentBuffer.clear()
            inComment = false
          }
          i++
          continue
        }

        when {
          char == '#' -> {
            // Process existing buffer before starting a comment
            processBufferedKeyword()

            // Start processing the comment
            commentBuffer.append(char)
            inComment = true
          }

          char.isLetterOrDigit() || char == '_' -> {
            // Accumulate potential keyword
            buffer.append(char)
          }

          else -> {
            // non-word character
            // ' ' (space), new line or tabulation
            processBufferedKeyword()

            // Append the non-word character
            append(char.toString())
          }
        }

        i++
      }

      // Handle any remaining keyword in the buffer
      processBufferedKeyword()
    }
  }
}

@Immutable
interface SyntaxHighlightProvider {
  @Composable
  fun rememberKeywords(): ImmutableMap<String, Color>
}

