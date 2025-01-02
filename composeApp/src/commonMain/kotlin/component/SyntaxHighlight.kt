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
      LineCommentProcessor(syntaxHighlight),
      KeywordsProcessor(keywords),
    )
  }

  return remember(code, syntaxHighlight) {
    buildAnnotatedString {
      var leftOver: String? = null
      var currentProcessor: Processor? = null

      var processorIndex = 0
      var i = 0
      var previousI = i

      while (i < code.length) {
        val processor = processors[processorIndex]
        val res = processor.process(
          code = code,
          i = i
        )
        when (res) {
          is Processor.Result.Commit -> {
            with(res) { change() }
            previousI = i
            processorIndex = 0
          }

          Processor.Result.NotMatched -> {
            i = previousI
            processorIndex++
          }

          Processor.Result.Processing -> TODO()
        }
      }
    }
  }
}

class LineCommentProcessor(
  syntaxHighlight: SyntaxHighlightProvider,
) : Processor {
  override val id = "line-comment"

  private var inComment = false
  private val lineComment = syntaxHighlight.lineComment

  private var startIndex: Int = Processor.NONE

  override fun process(
    code: String,
    i: Int,
  ): Processor.Result {
    if (startIndex == -1) {
      startIndex = i
    }
    return when {
      inComment -> {
        if (code[i] == '\n') {
          Processor.Result.Commit {
            withStyle(style = SpanStyle(color = Gray)) {
              append(code.slice(startIndex..i))
            }
          }.also {
            inComment = false
            startIndex = Processor.NONE
          }
        } else {
          Processor.Result.Processing
        }
      }

      startWithComment(code, i) -> {
        inComment = true
        Processor.Result.Processing
      }

      matchesComment(code, i) -> {
        Processor.Result.Processing
      }

      else -> {
        startIndex = Processor.NONE
        Processor.Result.NotMatched
      }
    }
  }

  private fun startWithComment(
    code: String,
    i: Int,
  ): Boolean {
    return (i - startIndex + 1) >= lineComment.length &&
        code.slice(startIndex until lineComment.length) == lineComment
  }

  private fun matchesComment(
    code: String,
    i: Int
  ): Boolean {
    for (j in startIndex..i) {
      if (code[i] != lineComment[j - startIndex]) {
        return true
      }
    }
    return true
  }

  override fun AnnotatedString.Builder.onInterrupt(code: String, i: Int) {}
}

class KeywordsProcessor(
  private val keywords: ImmutableMap<String, Color>,
) : Processor {
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
    code: String,
    i: Int,
  ): Result

  fun AnnotatedString.Builder.onInterrupt(
    code: String,
    i: Int,
  )

  sealed interface Result {
    data object Processing : Result
    data class Commit(val change: AnnotatedString.Builder.() -> Unit) : Result
    data object NotMatched : Result
  }

  companion object {
    val NONE = -1
  }
}

@Immutable
interface SyntaxHighlightProvider {
  @Composable
  fun rememberKeywords(): ImmutableMap<String, Color>

  val lineComment: String
}

