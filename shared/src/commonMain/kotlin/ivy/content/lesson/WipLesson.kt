package ivy.content.lesson

import ivy.model.TextStyle
import ivy.model.dsl.LessonContentScope
import ivy.model.dsl.codeBuilder
import ivy.model.dsl.lessonContent

fun wipLesson() = timeComplexityLesson()

fun timeComplexityLesson() = lessonContent {
  intro()
  whyQuestion()
  whyExplanation()
  internalsSection()
  mechanicsQuestions()
  addOpenQuestion()
  codeExample()
}

// Introduction
private fun LessonContentScope.intro() {
  text("intro") {
    text = "Understanding Time Complexity (Big O Notation)"
    style = TextStyle.Heading
  }
  text("intro_text") {
    text =
      "Time Complexity helps us analyze how the runtime of an algorithm grows with input size, enabling efficient algorithm design."
  }
}

// Why Question
private fun LessonContentScope.whyQuestion() {
  question("why_q") {
    question = "Why is understanding time complexity crucial for algorithm design?"
    clarification = "Think about how runtime impacts real-world applications."
    answer(
      text = "It helps predict algorithm performance.",
      correct = true,
      explanation = "Time complexity provides insights into runtime scalability."
    )
    answer(
      text = "It determines the algorithm's correctness.",
      explanation = "Correctness is separate from runtime efficiency."
    )
    answer(
      text = "It guarantees fast execution.",
      explanation = "Time complexity estimates growth trends, not absolute speed."
    )
  }
}

// Why Explanation
private fun LessonContentScope.whyExplanation() {
  text("why_explain") {
    text =
      "Time Complexity helps engineers evaluate algorithms before implementation, ensuring they can handle large-scale inputs efficiently."
    style = TextStyle.BodySpacingLarge
  }
}

// Internal Mechanics Section
private fun LessonContentScope.internalsSection() {
  text("internals") {
    text =
      "Big O Notation represents the upper bound of an algorithm's growth rate. Common complexities include O(1), O(n), O(log n), O(n^2), and so on."
    style = TextStyle.BodySpacingMedium
  }
}

// Mechanics Questions
private fun LessonContentScope.mechanicsQuestions() {
  question("mechanics_q1") {
    question = "What is the time complexity of finding the maximum element in an unsorted array?"
    clarification = "Consider the steps involved in traversing the array."
    answer(
      text = "O(1)",
      explanation = "This assumes constant-time operations, but finding a maximum requires traversal."
    )
    answer(
      text = "O(n)",
      correct = true,
      explanation = "We must iterate through the array once, making the complexity linear."
    )
    answer(
      text = "O(n^2)",
      explanation = "This would apply if we were comparing every pair of elements, which is unnecessary here."
    )
  }

  question("mechanics_q2") {
    question = "How does O(log n) differ from O(n)?"
    clarification = "Consider the number of operations required as input size doubles."
    answer(
      text = "O(log n) grows slower.",
      correct = true,
      explanation = "Logarithmic growth scales much more efficiently than linear growth."
    )
    answer(text = "O(n) grows slower.", explanation = "Linear growth scales faster than logarithmic growth.")
    answer(text = "They grow at the same rate.", explanation = "Linear and logarithmic growth rates are distinct.")
  }

  question("mechanics_q3") {
    question = "Which algorithm complexity is ideal for searching sorted data?"
    clarification = "Think about binary search and its efficiency."
    answer(text = "O(1)", explanation = "This applies to direct access, not searches.")
    answer(
      text = "O(log n)",
      correct = true,
      explanation = "Binary search operates logarithmically, dividing the problem space in half each step."
    )
    answer(text = "O(n)", explanation = "Linear search is less efficient for sorted data.")
  }
}

// Open Question
private fun LessonContentScope.addOpenQuestion() {
  openQuestion("open_q") {
    question = "Reflect: Can an O(n) algorithm ever be faster in practice than an O(log n) algorithm?"
    correctAnswer = "Yes, for small inputs or if constant factors in O(log n) outweigh those in O(n)."
  }
}

// Code Example
private fun LessonContentScope.codeExample() {
  text("code_example") {
    text = codeBuilder {
      line("// Example: Finding the maximum value in an array (O(n))")
      line("val numbers = listOf(3, 7, 2, 8, 4)")
      line("val max = numbers.maxOrNull()")
      line("println(\"Maximum value: \$max\")")
    }
  }
}
