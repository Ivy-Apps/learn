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
import kotlinx.collections.immutable.persistentListOf
import ui.theme.Gray

@Composable
fun highlightSyntax(
  code: String,
  syntaxHighlight: SyntaxHighlightProvider,
): AnnotatedString {
  val keywords = syntaxHighlight.rememberKeywords()

  val processors = remember {
    persistentListOf(
      LineCommentProcessor(),
      KeywordsProcessor(),
    )
  }

  return remember(code, syntaxHighlight) {
    buildAnnotatedString {
      var leftOver: String? = null
      var currentProcessor: Processor? = null
      for (char in code) {
        for (prc in processors) {
          val res = prc.process(
            leftOver = leftOver,
            syntaxHighlight = syntaxHighlight,
            keywords = keywords,
            char = char,
          )
          when (res) {
            is Processor.Result.NotMatched -> {
              currentProcessor = null
              leftOver = res.leftOver
            }

            Processor.Result.Processing -> {
              if (currentProcessor != null && prc.id != currentProcessor.id) {
                with(currentProcessor) {
                  onInterrupt(
                    leftOver = leftOver,
                    syntaxHighlight = syntaxHighlight,
                    keywords = keywords,
                    char = char
                  )
                }
              }
              currentProcessor = prc
              leftOver = null
              break
            }

            is Processor.Result.Commit -> {
              if (currentProcessor != null && prc.id != currentProcessor.id) {
                with(currentProcessor) {
                  onInterrupt(
                    leftOver = leftOver,
                    syntaxHighlight = syntaxHighlight,
                    keywords = keywords,
                    char = char
                  )
                }
              }
              currentProcessor = null
              with(res) {
                change()
              }
              leftOver = null
            }
          }
        }
      }
    }
  }
}

class LineCommentProcessor : Processor {
  override val id = "line-comment"

  private var inComment = false

  private val commentBuffer = StringBuilder()

  override fun process(
    leftOver: String?,
    syntaxHighlight: SyntaxHighlightProvider,
    keywords: ImmutableMap<String, Color>,
    char: Char
  ): Processor.Result {
    leftOver?.let(commentBuffer::append)
    val lineComment = syntaxHighlight.lineComment
    if (
      inComment ||
      (commentBuffer.isNotEmpty() && commentBuffer.startsWith(lineComment)) ||
      lineComment.startsWith(char)
    ) {
      commentBuffer.append(char)
      if (!inComment && lineComment in commentBuffer) {
        inComment = true
      }

      if (char == '\n') {
        // comment end
        return Processor.Result.Commit({
          withStyle(style = SpanStyle(color = Gray)) {
            append(commentBuffer.toString())
          }
        }).also {
          commentBuffer.clear()
          inComment = false
        }
      }
      return Processor.Result.Processing
    } else {
      return Processor.Result.NotMatched(
        leftOver = commentBuffer.toString()
      ).also {
        commentBuffer.clear()
      }
    }
  }

  override fun AnnotatedString.Builder.onInterrupt(
    leftOver: String?,
    syntaxHighlight: SyntaxHighlightProvider,
    keywords: ImmutableMap<String, Color>,
    char: Char
  ) {
    // do nothing
  }
}

class KeywordsProcessor : Processor {
  override val id = "keywords"

  private val buffer = StringBuilder()

  override fun process(
    leftOver: String?,
    syntaxHighlight: SyntaxHighlightProvider,
    keywords: ImmutableMap<String, Color>,
    char: Char
  ): Processor.Result {
    TODO()
  }

  private fun Char.isWordChar() = isLetterOrDigit() || this == '_'

  override fun AnnotatedString.Builder.onInterrupt(
    leftOver: String?,
    syntaxHighlight: SyntaxHighlightProvider,
    keywords: ImmutableMap<String, Color>,
    char: Char
  ) {
    TODO("Not yet implemented")
  }

}

interface Processor {
  val id: String

  fun process(
    leftOver: String?,
    syntaxHighlight: SyntaxHighlightProvider,
    keywords: ImmutableMap<String, Color>,
    char: Char
  ): Result

  fun AnnotatedString.Builder.onInterrupt(
    leftOver: String?,
    syntaxHighlight: SyntaxHighlightProvider,
    keywords: ImmutableMap<String, Color>,
    char: Char
  )

  sealed interface Result {
    data object Processing : Result
    data class Commit(val change: AnnotatedString.Builder.() -> Unit) : Result
    data class NotMatched(val leftOver: String) : Result
  }
}

@Immutable
interface SyntaxHighlightProvider {
  @Composable
  fun rememberKeywords(): ImmutableMap<String, Color>

  val lineComment: String
}

