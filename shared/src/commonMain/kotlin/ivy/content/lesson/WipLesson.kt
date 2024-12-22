import ivy.model.TextStyle
import ivy.model.dsl.codeBuilder
import ivy.model.dsl.lessonContent
import ivy.model.dsl.textBuilder

fun wipLesson() = lessonContent {
  text("intro_section") {
    style = TextStyle.Heading
    text = "1. Introduction"
  }

  text("intro_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Time Complexity (Big O) helps us compare how fast or slow an algorithm grows with respect to input size.")
      line("We're going to explore what Big O means and how to distinguish common complexities like O(1), O(n), O(logn), O(n^2), and O(nlogn).")
      newLine()
      line("Let's start with a simple question:")
    }
  }

  question("intro_question") {
    question = "What do you think happens to an algorithm's running time as we increase the size of its input?"
    clarification = "Reflect on how the algorithm changes when you have 10 inputs vs. 10,000 inputs."
    answer(
      text = "It stays the same always",
      explanation = "Not typically correct. Most algorithms take longer with larger inputs."
    )
    answer(
      text = "It grows in some predictable manner",
      correct = true,
      explanation = "Yes! This growth pattern is exactly what Big O attempts to describe."
    )
    answer(
      text = "It instantly crashes",
      explanation = "Crashes might happen if there's no proper handling, but not the usual measure."
    )
  }

  text("why_matters_heading") {
    style = TextStyle.Heading
    text = "2. Why Time Complexity Matters"
  }

  text("why_matters_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Big O notation helps us:")
      bullet("Predict performance changes as data size grows")
      bullet("Make informed decisions about which algorithm to use")
      bullet("Optimize our code to handle large inputs efficiently")
      newLine()
      line("Real-world analogy: Think of traveling by car vs. plane. For longer distances (bigger input), the choice of transport (algorithm) matters for speed.")
    }
  }

  question("why_matters_question") {
    question =
      "In your daily life, when do you notice the need for faster methods or shortcuts as tasks get bigger or more frequent?"
    clarification = "Try to connect this to traveling, cooking, or searching for information."
    answer(
      text = "When tasks get repetitive or huge",
      correct = true,
      explanation = "Exactly. The more work you have, the more important it is to find an efficient strategy."
    )
    answer(
      text = "Never",
      explanation = "Consider how searching for a piece of information is different when dealing with 10 items vs. 10,000 items."
    )
  }

  text("big_o_basics_heading") {
    style = TextStyle.Heading
    text = "3. Big O Basics"
  }

  text("big_o_basics_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Big O is a rough measure of how an algorithm’s runtime or space requirements grow as input grows.")
      line("Common complexities:")
      bullet("O(1): Constant time – doesn't grow with input.")
      bullet("O(n): Linear time – grows proportionally with input size.")
      bullet("O(logn): Logarithmic time – grows slowly, typical of divide-and-conquer (like binary search).")
      bullet("O(n^2): Quadratic time – grows quickly, typical of nested loops.")
      bullet("O(nlogn): Common for efficient sorting algorithms (like mergesort).")
    }
  }

  question("big_o_basics_question") {
    question = "Can you guess how many times the 'fast' vs. 'slow' algorithms loop or process steps for a large input?"
    clarification = "Think about each complexity's meaning."
    answer(
      text = "O(1) remains the same number of steps",
      correct = true,
      explanation = "Correct. No matter the input, it performs a constant number of operations."
    )
    answer(
      text = "O(n^2) means it loops linearly only once",
      explanation = "Actually, O(n^2) implies a nested loop structure, roughly n × n operations."
    )
  }

  text("detailed_breakdown_heading") {
    style = TextStyle.Heading
    text = "4. Detailed Breakdown of Key Complexities"
  }

  text("o1_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(1): Constant Time")
      line("Example: Accessing an element in an array by index.")
      newLine()
      line("Kotlin snippet:")
    }
  }

  text("o1_code") {
    style = TextStyle.Body
    text = codeBuilder {
      line("val numbers = listOf(3, 7, 2, 9)")
      line("// Accessing an element by index is O(1):")
      line("val element = numbers[2]  // This is constant time")
    }
  }

  question("o1_question") {
    question = "Why doesn't retrieving the 3rd element get slower if the list grows to 1000 elements?"
    clarification = "Consider how indexing works in memory."
    answer(
      text = "Because we still do one simple access operation",
      correct = true,
      explanation = "Correct. Element access by index is direct, no extra loops are needed."
    )
    answer(
      text = "Because the list sorts itself",
      explanation = "Sorting is unrelated here and would cost more than constant time."
    )
  }

  text("on_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(n): Linear Time")
      line("Example: Searching for a target in an unsorted list.")
      newLine()
      line("Kotlin snippet:")
    }
  }

  text("on_code") {
    style = TextStyle.Body
    text = codeBuilder {
      line("fun linearSearch(list: List<Int>, target: Int): Int? {")
      line("    for (i in list.indices) {")
      line("        if (list[i] == target) return i")
      line("    }")
      line("    return null")
      line("}")
    }
  }

  question("on_question") {
    question = "How many comparisons are made in the worst case for the linearSearch function if there are n elements?"
    clarification = "Think about the for-loop."
    answer(
      text = "n comparisons",
      correct = true,
      explanation = "Yes, in the worst case it checks each element once."
    )
    answer(
      text = "n^2 comparisons",
      explanation = "This would imply a double nested loop, which is not the case here."
    )
  }

  text("ologn_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(logn): Logarithmic Time")
      line("Example: Binary search on a sorted list.")
      newLine()
      line("Kotlin snippet:")
    }
  }

  text("ologn_code") {
    style = TextStyle.Body
    text = codeBuilder {
      line("fun binarySearch(sortedList: List<Int>, target: Int): Int? {")
      line("    var left = 0")
      line("    var right = sortedList.size - 1")
      line("    while (left <= right) {")
      line("        val mid = (left + right) / 2")
      line("        if (sortedList[mid] == target) return mid")
      line("        else if (sortedList[mid] < target) left = mid + 1")
      line("        else right = mid - 1")
      line("    }")
      line("    return null")
      line("}")
    }
  }

  question("ologn_question") {
    question = "Why does binarySearch only require log(n) checks?"
    clarification = "Focus on the while loop's dividing approach."
    answer(
      text = "Because each step narrows down the search space by half",
      correct = true,
      explanation = "Exactly. Halving repeatedly leads to a logarithmic growth in steps."
    )
    answer(
      text = "Because it still checks every element",
      explanation = "No, that would be linear (n)."
    )
  }

  text("on2_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(n^2): Quadratic Time")
      line("Example: A double nested loop comparing every item with every other item.")
      newLine()
      line("Kotlin snippet:")
    }
  }

  text("on2_code") {
    style = TextStyle.Body
    text = codeBuilder {
      line("fun pairwiseComparison(list: List<Int>) {")
      line("    for (i in list.indices) {")
      line("        for (j in list.indices) {")
      line("            // Compare list[i] and list[j]")
      line("        }")
      line("    }")
      line("}")
    }
  }

  question("on2_question") {
    question = "How many comparisons happen in pairwiseComparison if there are n elements?"
    clarification = "Look at the nested loops."
    answer(
      text = "n^2 comparisons",
      correct = true,
      explanation = "Right! Each of the n elements is compared with n elements, total n × n."
    )
    answer(
      text = "n comparisons",
      explanation = "That would be a single loop, not nested loops."
    )
  }

  text("onlogn_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(n log n): Common for efficient sorting like mergesort or quicksort.")
      newLine()
      line("Real-world analogy: Imagine repeatedly halving tasks (like logn) but still doing it n times in total.")
    }
  }

  question("onlogn_question") {
    question = "If mergesort runs in O(n log n), which operation do you suspect is repeated log n times?"
    clarification = "Relate to the divide-and-conquer approach."
    answer(
      text = "Splitting or merging steps performed at each divide level",
      correct = true,
      explanation = "Yes. The list is split in half (log n splits), and each split is processed across n items."
    )
    answer(
      text = "A simple pass over all elements once",
      explanation = "That would be O(n). The log factor indicates repeated divisions."
    )
  }

  text("interactive_exercises_heading") {
    style = TextStyle.Heading
    text = "5. Interactive Exercises for Differentiating Complexities"
  }

  text("interactive_exercises_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Activity: Label each scenario with its time complexity:")
      bullet("1) Checking if the first element of a list is > 0.")
      bullet("2) Counting vowels in a string of length n.")
      bullet("3) Splitting a list in half repeatedly until one element is left.")
      bullet("4) Comparing every name in a guest list with every other name.")
    }
  }

  question("exercise_question") {
    question = "Match the scenario to the complexity."
    clarification = "Think about the descriptions of each complexity so far."
    answer(
      text = "1) O(1), 2) O(n), 3) O(logn), 4) O(n^2)",
      correct = true,
      explanation = "Well done! Each scenario directly maps to the complexity described earlier."
    )
    answer(
      text = "1) O(n), 2) O(1), 3) O(n^2), 4) O(logn)",
      explanation = "Not quite. Revisit the definitions carefully."
    )
  }

  text("real_world_apps_heading") {
    style = TextStyle.Heading
    text = "6. Real-World Applications of Big O"
  }

  text("real_world_apps_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Searching for a product in an online store (binary search on sorted data).")
      line("Fetching a specific record from a database by key (O(1) if well-indexed).")
      line("Sorting large data sets for analytics (O(n log n) algorithms).")
    }
  }

  text("practical_identification_heading") {
    style = TextStyle.Heading
    text = "7. Practical Questions and Tricks to Identify Time Complexities"
  }

  text("practical_identification_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("1) Count the loops: Single loops often imply O(n), nested loops O(n^2).")
      line("2) Check for halving steps: Often indicates O(log n).")
      line("3) Combine patterns: If you do something n times that takes O(log n), it becomes O(n log n).")
      line("4) Constant time operations are usually direct array or hash lookups (O(1)).")
    }
  }

  question("identification_question") {
    question =
      "Imagine you're reading code with a for-loop, and inside it, there's a binary search. What complexity do you suspect?"
    clarification = "See #3 from the tips."
    answer(
      text = "O(n log n)",
      correct = true,
      explanation = "Yes! A for-loop (n times) and a log n search inside leads to O(n log n)."
    )
    answer(
      text = "O(n^2)",
      explanation = "That would be a nested loop scenario, not a single loop with a log n operation inside."
    )
  }

  text("reflection_heading") {
    style = TextStyle.Heading
    text = "8. Reflection and Problem-Solving"
  }

  text("reflection_body") {
    style = TextStyle.BodySpacingLarge
    text = textBuilder {
      line("Use the guiding questions below to further solidify your understanding:")
      bullet("In your own words, how would you explain Big O to a friend?")
      bullet("What is the biggest difference between O(log n) and O(n)?")
      bullet("How do you identify O(n^2) code quickly?")
      bullet("Why do you think O(n log n) sorting algorithms are considered efficient?")
      newLine()
      line("Remember: The best way to understand Big O is to practice analyzing different snippets of code. Ask yourself how many times each line runs as input grows.")
    }
  }
}