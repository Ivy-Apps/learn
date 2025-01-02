package component

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

@Immutable
class PythonSyntaxHighlight : SyntaxHighlightProvider {
  @Composable
  override fun rememberKeywords(): ImmutableMap<String, Color> {
    val keywordColor = MaterialTheme.colors.secondary
    val builtInFunColor = MaterialTheme.colors.primary
    return remember {
      persistentMapOf(
        // Control Flow
        "if" to keywordColor,
        "elif" to keywordColor,
        "else" to keywordColor,
        "for" to keywordColor,
        "while" to keywordColor,
        "break" to keywordColor,
        "continue" to keywordColor,
        "pass" to keywordColor,
        "return" to keywordColor,

        // Exception Handling
        "try" to keywordColor,
        "except" to keywordColor,
        "finally" to keywordColor,
        "raise" to keywordColor,
        "assert" to keywordColor,

        // Function and Variable Definition
        "def" to keywordColor,
        "lambda" to keywordColor,

        // Logical and Comparison
        "and" to keywordColor,
        "or" to keywordColor,
        "not" to keywordColor,
        "is" to keywordColor,
        "in" to keywordColor,

        // Class and Asynchronous
        "class" to keywordColor,
        "async" to keywordColor,
        "await" to keywordColor,

        // Other Keywords
        "import" to keywordColor,
        "from" to keywordColor,
        "as" to keywordColor,
        "with" to keywordColor,
        "yield" to keywordColor,
        "global" to keywordColor,
        "nonlocal" to keywordColor,
        "del" to keywordColor,
        "True" to keywordColor,
        "False" to keywordColor,
        "None" to keywordColor,

        // Built-in Functions
        "enumerate" to builtInFunColor,
        "range" to builtInFunColor,
        "len" to builtInFunColor,
        "print" to builtInFunColor,
        "input" to builtInFunColor,
        "int" to builtInFunColor,
        "float" to builtInFunColor,
        "str" to builtInFunColor,
        "list" to builtInFunColor,
        "dict" to builtInFunColor,
        "set" to builtInFunColor,
        "tuple" to builtInFunColor,
        "open" to builtInFunColor,
        "sum" to builtInFunColor,
        "min" to builtInFunColor,
        "max" to builtInFunColor,
        "abs" to builtInFunColor,
        "any" to builtInFunColor,
        "all" to builtInFunColor,
        "bin" to builtInFunColor,
        "bool" to builtInFunColor,
        "bytes" to builtInFunColor,
        "callable" to builtInFunColor,
        "chr" to builtInFunColor,
        "complex" to builtInFunColor,
        "dir" to builtInFunColor,
        "divmod" to builtInFunColor,
        "filter" to builtInFunColor,
        "format" to builtInFunColor,
        "getattr" to builtInFunColor,
        "hasattr" to builtInFunColor,
        "hash" to builtInFunColor,
        "hex" to builtInFunColor,
        "id" to builtInFunColor,
        "map" to builtInFunColor,
        "next" to builtInFunColor,
        "oct" to builtInFunColor,
        "ord" to builtInFunColor,
        "pow" to builtInFunColor,
        "repr" to builtInFunColor,
        "reversed" to builtInFunColor,
        "round" to builtInFunColor,
        "slice" to builtInFunColor,
        "sorted" to builtInFunColor,
        "staticmethod" to builtInFunColor,
        "super" to builtInFunColor,
        "type" to builtInFunColor,
        "vars" to builtInFunColor,
        "zip" to builtInFunColor
      )
    }
  }

  override val lineComment = "#"
}