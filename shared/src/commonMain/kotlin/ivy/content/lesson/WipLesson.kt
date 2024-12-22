import ivy.learn.TextStyle
import ivy.learn.dsl.codeBuilder
import ivy.learn.dsl.codeExample
import ivy.learn.dsl.lessonContent

fun wipLesson() = lessonContent {
  text("intro_heading") {
    text = "What is Time Complexity and Why Does it Matter?"
    style = TextStyle.Heading
  }

  text("intro_body") {
    text =
      "Imagine you are searching for a friend's phone number in a huge phonebook. If you randomly flip pages, it could take a long time. But if you follow an organized approach, like starting with the letter of their last name, you save time. Time complexity helps us understand how fast or slow an algorithm is as the size of the input grows."
    style = TextStyle.BodySpacingMedium
  }

  text("importance") {
    text =
      "Understanding time complexity helps us write efficient code, saving time and resources in real-world applications."
    style = TextStyle.BodySpacingLarge
  }

  text("big_o_intro") {
    text =
      "Big O notation is a way to describe the performance of an algorithm. It focuses on the worst-case scenario as input size increases. Let’s explore with an example!"
    style = TextStyle.BodySpacingMedium
  }

  codeExample("linear_search_code") {
    text = codeBuilder {
      line("# Python example: Searching for a number in a list")
      line("def search(array, target):")
      line("  for i in range(len(array)):")
      line("    if array[i] == target:")
      line("      return i")
      line("  return -1")
    }
  }

  text("linear_search_explained") {
    text =
      "This function searches through the list one by one until it finds the target or finishes checking the entire list."
    style = TextStyle.BodySpacingMedium
  }

  question("linear_search_question") {
    question = "If there are 10 elements in the list, in the worst case, how many checks does the function make?"
    clarification = "Think about when the target is not in the list."
    answer(text = "1", explanation = "Incorrect. The function checks all elements in the worst case.")
    answer(text = "5", explanation = "Incorrect. This would be true for the average case, not the worst case.")
    answer(
      text = "10",
      correct = true,
      explanation = "Correct! The function checks every element in the list in the worst case."
    )
    answer(text = "20", explanation = "No. There are only 10 elements in the list.")
  }

  text("linear_time_explained") {
    text =
      "In this example, the time complexity is O(n), where n is the number of elements in the list. This means the time it takes grows linearly with the input size."
    style = TextStyle.BodySpacingMedium
  }

  text("better_way") {
    text =
      "Now imagine searching for a word in a dictionary. You don’t check every word; you use the alphabetical order to skip many words. This is much faster."
    style = TextStyle.BodySpacingLarge
  }

  codeExample("binary_search_code") {
    text = codeBuilder {
      line("# Python example: Binary search")
      line("def binary_search(array, target):")
      line("  left, right = 0, len(array) - 1")
      line("  while left <= right:")
      line("    mid = (left + right) // 2")
      line("    if array[mid] == target:")
      line("      return mid")
      line("    elif array[mid] < target:")
      line("      left = mid + 1")
      line("    else:")
      line("      right = mid - 1")
      line("  return -1")
    }
  }

  text("binary_search_explained") {
    text =
      "This function uses a divide-and-conquer strategy to search. It splits the list into halves, eliminating half the possibilities each time."
    style = TextStyle.BodySpacingMedium
  }

  question("binary_search_question") {
    question = "If there are 100 elements in the list, in the worst case, how many checks does the function make?"
    clarification = "Remember, the function splits the list in half each time."
    answer(text = "100", explanation = "Incorrect. This would be true for a linear search, not binary search.")
    answer(text = "50", explanation = "No, binary search reduces the problem by halving, not checking every element.")
    answer(text = "7", explanation = "Close, but not quite. Try calculating log2(100).")
    answer(
      text = "7 (approximately)",
      correct = true,
      explanation = "Correct! Binary search performs about log2(n) checks in the worst case."
    )
  }

  text("log_time_explained") {
    text =
      "In this example, the time complexity is O(log n), where n is the number of elements. This means the time grows much slower compared to O(n)."
    style = TextStyle.BodySpacingMedium
  }

  text("summary") {
    text =
      "Time complexity helps us compare algorithms. Linear search (O(n)) is slower for large inputs, while binary search (O(log n)) is faster. Understanding this helps us write better code!"
    style = TextStyle.BodySpacingLarge
  }
}