import ivy.model.TextStyle
import ivy.model.dsl.LessonContentScope
import ivy.model.dsl.codeBuilder
import ivy.model.dsl.codeExample
import ivy.model.dsl.lessonContent

fun bigONotationLesson() = lessonContent {
  intro()
  whyQuestion()
  whyExplanation()
  bigOBasicsSection()
  detailedBreakdownSection()
  realWorldApplicationsSection()
  reflectionSection()
}

// Introduction
private fun LessonContentScope.intro() {
  text("intro") {
    text = "Understanding Time Complexity (Big O Notation)"
    style = TextStyle.Heading
  }
  text("intro_text") {
    text =
      "Big O notation helps us analyze how efficiently algorithms perform as the input size grows. It's a key concept for writing optimized code."
  }
}

// Why Time Complexity Matters
private fun LessonContentScope.whyQuestion() {
  question("why_q") {
    question = "Why is understanding time complexity important?"
    clarification = "Think about how it affects algorithm performance and code efficiency."
    answer(
      text = "It helps optimize code by identifying slow algorithms.",
      correct = true,
      explanation = "Time complexity guides us to choose the best algorithms for performance."
    )
    answer(
      text = "It's a programming syntax.",
      explanation = "This is incorrect. Big O notation is not syntax but a tool for performance analysis."
    )
    answer(
      text = "It only applies to large input sizes.",
      explanation = "While it's most significant for large inputs, it applies universally."
    )
    answer(
      text = "It guarantees fast execution.",
      explanation = "Big O analysis doesn't guarantee execution speed but estimates efficiency."
    )
  }
}

// Why Explanation
private fun LessonContentScope.whyExplanation() {
  text("why_explain") {
    text =
      "Understanding time complexity helps developers write efficient algorithms, save computational resources, and improve user experience. It also enables comparisons between different approaches to solve the same problem."
    style = TextStyle.BodySpacingLarge
  }
}

// Big O Basics
private fun LessonContentScope.bigOBasicsSection() {
  text("big_o_basics") {
    text =
      "Big O notation describes how an algorithm's runtime or space requirements grow relative to input size. It uses symbols like O(1), O(n), O(log n), and O(n^2)."
    style = TextStyle.BodySpacingMedium
  }
  image("big_o_graph") {
    imageUrl = "https://example.com/big_o_graph.png"
  }
}

// Detailed Breakdown of Key Complexities
private fun LessonContentScope.detailedBreakdownSection() {
  text("complexity_breakdown") {
    text = "Let's examine the most common time complexities in detail with examples."
    style = TextStyle.BodySpacingMedium
  }
  codeExample("constant_time") {
    text = codeBuilder {
      line("// O(1) - Constant Time Example")
      line("fun accessElement(list: List<Int>, index: Int): Int {")
      line("    return list[index]") // Accessing an element by index
      line("}")
    }
  }
  codeExample("linear_time") {
    text = codeBuilder {
      line("// O(n) - Linear Time Example")
      line("fun printList(list: List<Int>) {")
      line("    for (item in list) {")
      line("        println(item)") // Traversing the entire list
      line("    }")
      line("}")
    }
  }
  codeExample("logarithmic_time") {
    text = codeBuilder {
      line("// O(log n) - Logarithmic Time Example")
      line("fun binarySearch(list: List<Int>, target: Int): Int {")
      line("    var left = 0")
      line("    var right = list.size - 1")
      line("    while (left <= right) {")
      line("        val mid = (left + right) / 2")
      line("        if (list[mid] == target) return mid")
      line("        else if (list[mid] < target) left = mid + 1")
      line("        else right = mid - 1")
      line("    }")
      line("    return -1")
      line("}")
    }
  }
  codeExample("quadratic_time") {
    text = codeBuilder {
      line("// O(n^2) - Quadratic Time Example")
      line("fun printPairs(list: List<Int>) {")
      line("    for (i in list.indices) {")
      line("        for (j in list.indices) {")
      line("            println(\"(\${list[i]}, \${list[j]})\")") // Nested loops
      line("        }")
      line("    }")
      line("}")
    }
  }
}

// Real-World Applications of Big O
private fun LessonContentScope.realWorldApplicationsSection() {
  text("real_world_applications") {
    text =
      "Big O notation is essential for optimizing search engines, scheduling tasks, and improving the speed of mobile apps and websites."
    style = TextStyle.BodySpacingLarge
  }
  image("real_world_example") {
    imageUrl = "https://example.com/real_world_example.png"
  }
}

// Reflection and Problem-Solving
private fun LessonContentScope.reflectionSection() {
  text("reflection") {
    text =
      "Reflect on how you can apply Big O concepts to optimize your code. Which algorithms in your recent projects could be improved?"
    style = TextStyle.BodySpacingMedium
  }
  question("reflection_q") {
    question = "Which of these algorithms is the most efficient for sorting?"
    clarification = "Think about the time complexities of common sorting algorithms."
    answer(
      text = "QuickSort (Average case O(n log n))",
      correct = true,
      explanation = "QuickSort is efficient for average cases."
    )
    answer(text = "BubbleSort (O(n^2))", explanation = "BubbleSort is inefficient for large datasets.")
    answer(
      text = "MergeSort (O(n log n))",
      correct = true,
      explanation = "MergeSort is efficient and guarantees O(n log n) complexity."
    )
    answer(
      text = "SelectionSort (O(n^2))",
      explanation = "SelectionSort is inefficient compared to QuickSort and MergeSort."
    )
  }
}
