import ivy.model.TextStyle
import ivy.model.dsl.*

fun timeComplexityLesson() = lessonContent {
  introductionSection()
  whyTimeComplexityMattersSection()
  bigOBasicsSection()
  detailedBreakdownSection()
  interactiveExercisesSection()
  realWorldApplicationsSection()
  reflectionSection()
}

// 1. Introduction
private fun LessonContentScope.introductionSection() {
  text("intro_title_tc") {
    text = "Introduction: Time Complexity & Big O Notation"
    style = TextStyle.Heading
  }
  text("intro_text_tc") {
    text =
      "Time Complexity (Big O) measures how efficient an algorithm is by describing how its runtime or steps grow with input size."
    style = TextStyle.BodySpacingMedium
  }
}

// 2. Why Time Complexity Matters
private fun LessonContentScope.whyTimeComplexityMattersSection() {
  question("why_time_complexity_q") {
    question = "Why should we care about Time Complexity?"
    clarification = "Think about how performance impacts everyday computing."
    answer(
      text = "Because efficiency matters for handling large data.",
      correct = true,
      explanation = "Yes, Big O helps us manage performance effectively."
    )
    answer(
      text = "It’s only useful for large-scale supercomputers.",
      explanation = "Actually, it’s relevant for both small and large inputs."
    )
    answer(
      text = "Time Complexity doesn’t matter in practice.",
      explanation = "Incorrect; time complexity is crucial for real-world apps."
    )
    answer(
      text = "It’s a marketing gimmick.",
      explanation = "Not correct. It’s a formal tool for measuring performance."
    )
  }
  text("why_time_complexity_text") {
    text =
      "Time Complexity is essential because it predicts whether a solution will scale. It saves resources and helps you write efficient code."
    style = TextStyle.BodySpacingLarge
  }
}

// 3. Big O Basics
private fun LessonContentScope.bigOBasicsSection() {
  text("big_o_basics_heading") {
    text = "Big O Basics"
    style = TextStyle.Heading
  }
  text("big_o_basics_explain") {
    text = textBuilder {
      line("Big O notation describes the upper bound of an algorithm’s growth rate.")
      line("Common notations include:")
      bullet("O(1) – Constant time")
      bullet("O(n) – Linear time")
      bullet("O(log n) – Logarithmic time")
      bullet("O(n^2) – Quadratic time")
      bullet("O(n log n) – Log-linear time")
    }
    style = TextStyle.BodySpacingMedium
  }
  image("big_o_chart_img") {
    imageUrl = "https://example.com/bigOChart.png"
  }
}

// 4. Detailed Breakdown of Key Complexities
private fun LessonContentScope.detailedBreakdownSection() {
  text("detailed_breakdown_heading") {
    text = "Detailed Breakdown"
    style = TextStyle.Heading
  }
  text("detailed_breakdown_text") {
    text = textBuilder {
      line("• O(1): Constant time — e.g., accessing an array element by index.")
      line("• O(n): Linear time — e.g., iterating through a list.")
      line("• O(log n): Logarithmic time — e.g., binary search on a sorted array.")
      line("• O(n^2): Quadratic time — e.g., a nested loop checking every pair.")
      line("• O(n log n): Log-linear time — e.g., efficient sorting algorithms like mergesort.")
    }
    style = TextStyle.BodySpacingMedium
  }
  codeExample("detailed_breakdown_code_example") {
    text = codeBuilder {
      line("# Python examples of different time complexities")
      line("")
      line("def constant_time(items):")
      line("    return items[0]  # O(1)")
      line("")
      line("def linear_time(items):")
      line("    for item in items:")
      line("        print(item)  # O(n)")
      line("")
      line("def quadratic_time(items):")
      line("    for i in range(len(items)):")
      line("        for j in range(len(items)):")
      line("            print(items[i], items[j])  # O(n^2)")
    }
  }
}

// 5. Interactive Exercises for Differentiating Complexities
private fun LessonContentScope.interactiveExercisesSection() {
  question("complexities_match_q") {
    question = "Match the function to its Big O complexity."
    clarification = "Identify which function calls are O(1), O(n), and O(n^2)."
    answer(
      text = "Accessing an element by index -> O(1)",
      correct = true,
      explanation = "Random access in constant time."
    )
    answer(text = "Single loop over items -> O(n)", correct = true, explanation = "Looping once over n items.")
    answer(text = "Nested loops -> O(n^2)", correct = true, explanation = "Two nested loops lead to n*n operations.")
    answer(text = "All are O(n)", explanation = "Incorrect; each function has a distinct complexity.")
  }
  question("log_n_question") {
    question = "Which of these is an example of O(log n)?"
    clarification = "Think about halving the search space each step."
    answer(
      text = "Binary search on a sorted array",
      correct = true,
      explanation = "Search space splits in half each iteration."
    )
    answer(text = "Searching every element in a list", explanation = "That’s O(n).")
    answer(text = "Nested loops over a 2D matrix", explanation = "That’s O(n^2).")
    answer(text = "Simple assignment", explanation = "That’s O(1).")
  }
  question("multiple_correct_n_log_n") {
    question = "Which statements about O(n log n) are true?"
    clarification = "Select all valid statements."
    answer(
      text = "Algorithms like mergesort run in O(n log n)",
      correct = true,
      explanation = "Mergesort is a classic O(n log n) example."
    )
    answer(
      text = "It’s faster than O(log n) for large n",
      explanation = "Incorrect; O(log n) grows slower than O(n log n)."
    )
    answer(
      text = "It’s faster than O(n^2) for very large n",
      correct = true,
      explanation = "Yes, n^2 grows faster than n log n."
    )
    answer(text = "It’s the same as O(n)", explanation = "Not correct. n log n grows faster than n.")
  }
}

// 6. Real-World Applications of Big O
private fun LessonContentScope.realWorldApplicationsSection() {
  text("real_world_heading") {
    text = "Real-World Applications of Big O"
    style = TextStyle.Heading
  }
  text("real_world_body") {
    text = textBuilder {
      line("• Search Engines: Use O(log n) or O(n log n) algorithms to efficiently query massive datasets.")
      line("• E-commerce: Sorting products (O(n log n)) and handling user interactions at scale (O(1) or O(n) tasks).")
      line("• AI & ML: Choosing the right algorithm can save on computing resources when dealing with large training data.")
    }
    style = TextStyle.BodySpacingMedium
  }
  question("real_world_question") {
    question = "True or False: Time Complexity is crucial in deciding how an application scales."
    clarification = "Scaling refers to handling more users/data without unacceptable slowdowns."
    answer(text = "True", correct = true, explanation = "Time Complexity directly influences app scalability.")
    answer(text = "False", explanation = "Performance is always a factor, so this is incorrect.")
  }
}

// 7. Reflection and Problem-Solving
private fun LessonContentScope.reflectionSection() {
  text("reflection_heading") {
    text = "Reflection & Problem-Solving"
    style = TextStyle.Heading
  }
  text("reflection_body") {
    text = textBuilder {
      line("Now you understand Big O basics and the differences between O(1), O(n), O(log n), O(n^2), and O(n log n).")
      line("Use this knowledge to choose the right algorithm for your next problem.")
    }
    style = TextStyle.BodySpacingLarge
  }
  question("final_reflection_q") {
    question = "Which of the following best summarizes Big O notation?"
    clarification = "Select the most fitting answer."
    answer(
      text = "A way to measure how code runtime scales with input size",
      correct = true,
      explanation = "This captures the core idea of Big O."
    )
    answer(text = "A random guess about performance", explanation = "Big O is not random; it’s a formal method.")
    answer(
      text = "It only applies to sorting algorithms",
      explanation = "It applies to all algorithmic performance, not just sorting."
    )
    answer(
      text = "It’s about hardware specs",
      explanation = "Big O focuses on algorithmic steps, not specific hardware."
    )
  }
}