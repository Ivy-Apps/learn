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
      line("Welcome to Time Complexity (Big O)!")
      line("Big O shows how an algorithm’s running time grows when the input gets bigger.")
      newLine()
      line("Let’s start with a question:")
    }
  }

  question("intro_question") {
    question = "What happens to an algorithm’s runtime when we increase its input size?"
    clarification = "Think about 10 items vs. 10 million items."
    answer(
      text = "It never changes",
      explanation = "That is rare. Most algorithms slow down when more data comes in."
    )
    answer(
      text = "It grows in a specific way (like linear, quadratic, etc.)",
      correct = true,
      explanation = "Yes. Big O describes how quickly it grows."
    )
    answer(
      text = "It crashes right away",
      explanation = "Crashes can happen, but that’s not about time complexity."
    )
    answer(
      text = "It doubles no matter what",
      explanation = "Not always. Some algorithms might double, others might do something else."
    )
  }

  text("why_matters_heading") {
    style = TextStyle.Heading
    text = "2. Why Time Complexity Matters"
  }

  text("why_matters_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Big O helps you:")
      bullet("Predict how your program will handle large inputs.")
      bullet("Choose the right approach for your problem.")
      bullet("Avoid slow performance when data grows.")
      newLine()
      line("Example: Walking vs. flying. For short distances, walking is fine. For long distances, flying is much faster. Algorithms work the same way.")
    }
  }

  question("why_matters_question") {
    question = "Which real-life situation best shows that we need better methods for bigger tasks?"
    clarification = "Think about tasks that get bigger, like laundry or searching a messy room."
    answer(
      text = "Doing a single small task at home",
      explanation = "Small tasks don’t show big differences in methods."
    )
    answer(
      text = "Doing a large pile of laundry fast",
      correct = true,
      explanation = "Yes! Bigger tasks need better strategies."
    )
    answer(
      text = "Taking one file from a drawer",
      explanation = "That’s not much data, so efficiency isn’t a big concern."
    )
    answer(
      text = "Turning off the lights",
      explanation = "That’s almost instant, like a constant-time action."
    )
  }

  text("big_o_basics_heading") {
    style = TextStyle.Heading
    text = "3. Big O Basics"
  }

  text("big_o_basics_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Big O shows how runtime changes if we keep increasing the input size.")
      line("Common Big O complexities:")
      bullet("O(1): Constant time – the runtime stays the same, no matter the input.")
      bullet("O(n): Linear – the runtime grows in direct proportion to n.")
      bullet("O(log n): Logarithmic – the runtime grows slowly, like in binary search.")
      bullet("O(n^2): Quadratic – often caused by nested loops.")
      bullet("O(n log n): A mix of linear and log steps, common in sorting.")
    }
  }

  question("big_o_basics_question") {
    question = "Which idea describes O(n^2)?"
    clarification = "Think about double nested loops or pair comparisons."
    answer(
      text = "Checking every item once (one loop)",
      explanation = "That is O(n)."
    )
    answer(
      text = "A constant-time operation",
      explanation = "That’s O(1)."
    )
    answer(
      text = "Comparing each item with every other item",
      correct = true,
      explanation = "Yes. That usually means n * n comparisons."
    )
    answer(
      text = "Splitting data in half repeatedly",
      explanation = "That suggests O(log n) or O(n log n)."
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
      line("These operations take the same time, even if you jump from 10 to 10,000 items.")
      newLine()
      line("Example in Kotlin:")
    }
  }

  text("o1_code") {
    style = TextStyle.Body
    text = codeBuilder {
      line("val numbers = listOf(3, 7, 2, 9)")
      line("// Access an element by index:")
      line("val element = numbers[2] // O(1)")
    }
  }

  question("o1_question") {
    question = "Why is accessing numbers[2] O(1)?"
    clarification = "Think about direct indexing in memory."
    answer(
      text = "Because it looks at all elements each time",
      explanation = "That would be O(n)."
    )
    answer(
      text = "Because it goes straight to that spot in memory",
      correct = true,
      explanation = "Exactly. Arrays let you jump right to the index."
    )
    answer(
      text = "Because it depends on a fancy sorting method",
      explanation = "Sorting is not related here."
    )
    answer(
      text = "Because the element does not exist",
      explanation = "It does exist. We can access it directly."
    )
  }

  text("on_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(n): Linear Time")
      line("Doubling the input will about double the runtime.")
      newLine()
      line("Kotlin example:")
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
    question = "In the worst case, how many checks does linearSearch do for a list of size n?"
    clarification = "Think about a loop from start to finish."
    answer(
      text = "n checks",
      correct = true,
      explanation = "Yes. We might check every element once."
    )
    answer(
      text = "n^2 checks",
      explanation = "That would need nested loops."
    )
    answer(
      text = "log n checks",
      explanation = "That is binary search, which cuts in half each time."
    )
    answer(
      text = "1 check",
      explanation = "That would be constant time, not a full loop."
    )
  }

  text("ologn_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(log n): Logarithmic Time")
      line("Each step cuts the input in half. A bigger input only adds a few extra steps.")
      newLine()
      line("Kotlin example:")
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
    question = "Why is binarySearch O(log n)?"
    clarification = "Look at how the search space changes in each loop."
    answer(
      text = "It checks every element one by one",
      explanation = "That is linear, O(n)."
    )
    answer(
      text = "It removes half of the data each time",
      correct = true,
      explanation = "Yes. Halving each time is a log n pattern."
    )
    answer(
      text = "It needs two nested loops",
      explanation = "No. There is just one while loop."
    )
    answer(
      text = "It only works for large n",
      explanation = "Log n is about halving, not about needing a huge input."
    )
  }

  text("on2_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(n^2): Quadratic Time")
      line("A common case is a double loop, where each item is compared to every other item.")
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
      line("            // Compare list[i] to list[j]")
      line("        }")
      line("    }")
      line("}")
    }
  }

  question("on2_question") {
    question = "How many comparisons happen if there are n elements?"
    clarification = "Look at the two nested loops."
    answer(
      text = "n comparisons",
      explanation = "One loop is n. But we have a second loop inside it."
    )
    answer(
      text = "n^2 comparisons",
      correct = true,
      explanation = "Yes! Each of the n elements compares to n others."
    )
    answer(
      text = "1 comparison",
      explanation = "That would be constant time."
    )
    answer(
      text = "log n comparisons",
      explanation = "That would be halving. Not happening here."
    )
  }

  text("onlogn_example") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("O(n log n): This is common in efficient sorting (like mergesort).")
      line("Think of it as splitting data in half (log n) but doing it across n items.")
    }
  }

  question("onlogn_question") {
    question = "If mergesort is O(n log n), which part is the log n part?"
    clarification = "Mergesort splits the list in halves repeatedly."
    answer(
      text = "Comparing each element with every other",
      explanation = "That is O(n^2)."
    )
    answer(
      text = "Splitting the data in half many times",
      correct = true,
      explanation = "Yes! Each split reduces the problem to half."
    )
    answer(
      text = "Sorting just two items",
      explanation = "That’s a smaller sub-problem, not the overall approach."
    )
    answer(
      text = "Accessing an element by index",
      explanation = "That is O(1)."
    )
  }

  text("interactive_exercises_heading") {
    style = TextStyle.Heading
    text = "5. Interactive Exercises: Identify Complexities"
  }

  text("interactive_exercises_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("Try these scenarios and see which complexity fits.")
      bullet("1) Getting the last element of a list directly.")
      bullet("2) Going through a list of items from start to finish.")
      bullet("3) Splitting a list in half, then half again, until only one item remains.")
      bullet("4) Comparing each item with every other item in a list.")
    }
  }

  question("exercise_question") {
    question = "Match each scenario to O(1), O(n), O(log n), or O(n^2)."
    clarification = "Think about the patterns you learned."
    answer(
      text = "1) O(1), 2) O(n), 3) O(log n), 4) O(n^2)",
      correct = true,
      explanation = "Well done! Each one fits the pattern described."
    )
    answer(
      text = "All are O(1)",
      explanation = "That means no change with bigger data, which is not true."
    )
    answer(
      text = "1) O(n), 2) O(n^2), 3) O(1), 4) O(log n)",
      explanation = "This does not match the logic of each action."
    )
    answer(
      text = "Everything is O(n^2)",
      explanation = "We only get O(n^2) when loops are nested."
    )
  }

  text("real_world_apps_heading") {
    style = TextStyle.Heading
    text = "6. Real-World Applications"
  }

  text("real_world_apps_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("• Searching a large online store: can be O(log n) if sorted by product ID.")
      line("• Sorting thousands of photos: often O(n log n) with modern sorting algorithms.")
      line("• Looking up a key in a fast database: can be O(1) with the right data structure.")
    }
  }

  text("practical_identification_heading") {
    style = TextStyle.Heading
    text = "7. Tips to Spot Time Complexities"
  }

  text("practical_identification_body") {
    style = TextStyle.BodySpacingMedium
    text = textBuilder {
      line("1) One simple loop from 0 to n is O(n).")
      line("2) A loop inside another loop is O(n^2).")
      line("3) Halving the data each time is O(log n).")
      line("4) Doing something O(log n) times for all n elements is O(n log n).")
      line("5) Direct index access in an array is O(1).")
    }
  }

  question("identification_question") {
    question =
      "If a loop runs n times, and inside it we do a binary search (which is O(log n)), what is the total complexity?"
    clarification = "Combine O(n) and O(log n)."
    answer(
      text = "O(n^2)",
      explanation = "That would need nested loops each going up to n."
    )
    answer(
      text = "O(n log n)",
      correct = true,
      explanation = "Yes. n times for the loop * log n for the search."
    )
    answer(
      text = "O(1)",
      explanation = "It clearly depends on n, so it grows."
    )
    answer(
      text = "O(log n)",
      explanation = "We also have that outer loop running n times."
    )
  }

  text("reflection_heading") {
    style = TextStyle.Heading
    text = "8. Reflection and Next Steps"
  }

  text("reflection_body") {
    style = TextStyle.BodySpacingLarge
    text = textBuilder {
      line("You did it! You now know O(1), O(n), O(log n), O(n^2), and O(n log n).")
      bullet("Try explaining Big O to a friend using a real example.")
      bullet("When coding, think: 'What happens if n doubles?'")
      bullet("Practice with more code to sharpen your Big O skills.")
      newLine()
      line("Great job! Enjoy writing faster programs and smarter solutions!")
    }
  }
}