import ivy.learn.TextStyle
import ivy.learn.dsl.lessonContent

fun wipLesson() = lessonContent {

  // 1. Introduction
  text("intro_heading") {
    style = TextStyle.Heading
    text = "Time Complexity: What and Why?"
  }

  code("brute_force_pin**") {
    line("# Brute-forcing a 4-digit PIN")
    line("def brute_force_pin():")
    line("    for pin in range(10000): # 0000 to 9999")
    line("        # Try the PIN")
    line("        if test_pin(pin):")
    line("            return pin")
  }

  image("sdsd") {
    imageUrl = "invalid"
  }

  image("phonebook_img") {
    imageUrl =
      "https://i.ibb.co/Xy2zQ44/DALL-E-2024-12-23-00-03-54-A-visual-analogy-for-time-complexity-a-person-flipping-through-a-giant-ph.webp"
  }

  image("time-complexity-diagram") {
    imageUrl = "https://i.ibb.co/b71s1Xy/time-complexity-diagram.jpg"
  }

  code("demo-code") {
    line("# A simple linear search in Python")
    line("def find_number(phonebook, friend_name):")
    line("    for i, entry in enumerate(phonebook):")
    line("        if entry == friend_name:")
    line("            return i")
    line("    return -1")
  }

  code("constant_time_example**") {
    line("# O(1): Accessing the first element is always one step")
    line("def get_first_element(items):")
    line("    return items[0]")
  }

  code("log_time_example**") {
    line("# O(log n): Binary search example (requires a sorted list)")
    line("def find_item(sorted_list, target):")
    line("    low, high = 0, len(sorted_list) - 1")
    line("    while low <= high:")
    line("        mid = (low + high) // 2")
    line("        if sorted_list[mid] == target:")
    line("            return mid")
    line("        elif sorted_list[mid] < target:")
    line("            low = mid + 1")
    line("        else:")
    line("            high = mid - 1")
    line("    return -1")
  }

  code("linear_time_example**") {
    line("# O(n): Print each item in the list once")
    line("def print_all_items(items):")
    line("    for x in items:")
    line("        print(x)")
  }

  code("n_log_n_example**") {
    line("# O(n log n): Simplified merge sort example")
    line("def merge_sort(items):")
    line("    if len(items) <= 1:")
    line("        return items")
    line("    mid = len(items) // 2")
    line("    left = merge_sort(items[:mid])")
    line("    right = merge_sort(items[mid:])")
    line("    return merge(left, right)")

    line("")
    line("def merge(left, right):")
    line("    result = []")
    line("    i = j = 0")
    line("    while i < len(left) and j < len(right):")
    line("        if left[i] < right[j]:")
    line("            result.append(left[i])")
    line("            i += 1")
    line("        else:")
    line("            result.append(right[j])")
    line("            j += 1")
    line("    result.extend(left[i:])")
    line("    result.extend(right[j:])")
    line("    return result")
  }

  code("quadratic_time_example**") {
    line("# O(n^2): Compare every item with every other item")
    line("def compare_pairs(items):")
    line("    for i in range(len(items)):")
    line("        for j in range(len(items)):")
    line("            if i != j:")
    line("                print(items[i], items[j])")
  }

  text("intro_body") {
    style = TextStyle.Body
    text =
      "Time complexity measures how fast an algorithm runs as the input grows. It helps us compare methods and pick the best one."
  }

  // 2. Introductory Question
  question("why_care_question") {
    question = "Why should we care about time complexity when writing code?"
    clarification = "Think about how your code might behave as data grows."

    answer(
      text = "We don't need to; all code runs instantly",
      explanation = "Not true. Large inputs slow down code; time complexity helps us plan.",
      correct = false
    )

    answer(
      text = "It tells us how difficult the code is to write",
      explanation = "No. Time complexity is about performance, not coding difficulty.",
      correct = false
    )

    answer(
      text = "It lets us predict performance for big inputs",
      explanation = "Correct! Time complexity shows how an algorithm scales.",
      correct = true
    )

    answer(
      text = "It's only for math geniuses",
      explanation = "Time complexity basics are for everyone, not just math experts.",
      correct = false
    )
  }

  // 3. Simple Analogy
  text("analogy_body") {
    style = TextStyle.BodySpacingMedium
    text =
      "Imagine looking for a friend's number in a giant phonebook. If you check each page, it takes longer as the book grows. That's time complexity!"
  }

  // 4. Code Example (Linear Search)
  code("linear_search_example") {
    line("# A simple linear search in Python")
    line("def find_number(phonebook, friend_name):")
    line("    for i, entry in enumerate(phonebook):")
    line("        if entry == friend_name:")
    line("            return i")
    line("    return -1")
  }

  // 5. Question: Linear Search
  question("linear_search_question") {
    question = "How many steps does the linear search take in the worst case?"
    clarification = "Think of how you scan one by one."

    answer(
      text = "It checks every entry, so it grows with the size of the list",
      explanation = "Correct. That's O(n).",
      correct = true
    )

    answer(
      text = "It magically finds the entry with just one check",
      explanation = "Nope. That would be O(1), which is not how linear search works.",
      correct = false
    )

    answer(
      text = "It does half the work for each check, so it’s O(log n)",
      explanation = "This describes binary search, not linear search.",
      correct = false
    )

    answer(
      text = "Time complexity does not change with list size",
      explanation = "It does change; a bigger list means more steps.",
      correct = false
    )
  }

  // 6. Binary Search Code Example
  code("binary_search_example") {
    line("# A simple binary search in Python")
    line("# Note: This requires a sorted list.")
    line("def find_number_binary(phonebook, friend_name):")
    line("    low = 0")
    line("    high = len(phonebook) - 1")
    line("    while low <= high:")
    line("        mid = (low + high) // 2")
    line("        if phonebook[mid] == friend_name:")
    line("            return mid")
    line("        elif phonebook[mid] < friend_name:")
    line("            low = mid + 1")
    line("        else:")
    line("            high = mid - 1")
    line("    return -1")
  }

  // 7. Question: Binary Search
  question("binary_search_question") {
    question = "Why is binary search faster than linear search on a sorted list?"
    clarification = "Think about how each guess cuts the search range in half."

    answer(
      text = "Because it does not need the list to be sorted",
      explanation = "Incorrect. Binary search only works correctly when the list is sorted.",
      correct = false
    )

    answer(
      text = "It checks each element one by one",
      explanation = "No, that's linear search, not binary search.",
      correct = false
    )

    answer(
      text = "It splits the list in half each time, so it only takes O(log n) steps",
      explanation = "Correct! Each comparison halves the search space.",
      correct = true
    )

    answer(
      text = "It tries every possible entry in random order",
      explanation = "No, random guesses would not guarantee O(log n).",
      correct = false
    )
  }

  // 8. Big O Explanation
  text("big_o_body") {
    style = TextStyle.BodySpacingLarge
    text =
      "Big O notation describes how an algorithm’s runtime grows with the input size. For example, O(n) means the time grows in direct proportion to n, and O(log n) means the time grows much slower."
  }

  // 9. Brute Force PIN Example
  code("brute_force_pin") {
    line("# Brute-forcing a 4-digit PIN")
    line("def brute_force_pin():")
    line("    for pin in range(10000): # 0000 to 9999")
    line("        # Try the PIN")
    line("        if test_pin(pin):")
    line("            return pin")
  }

  // 10. Question: PIN Complexity
  question("pin_complexity_question") {
    question = "When brute-forcing a 4-digit PIN, how does the runtime grow with more digits?"
    clarification = "Think about how many tries you need as digits increase."

    answer(
      text = "It doesn’t grow; always 4 digits",
      explanation = "Incorrect. Adding more digits means more possibilities.",
      correct = false
    )

    answer(
      text = "It grows linearly with the number of digits (O(n))",
      explanation = "Not exactly; each extra digit multiplies possibilities by 10.",
      correct = false
    )

    answer(
      text = "It grows exponentially with the number of digits (10^n)",
      explanation = "Correct! Each new digit multiplies the attempts by 10.",
      correct = true
    )

    answer(
      text = "It becomes constant for any length of PIN",
      explanation = "No. More digits always mean more attempts.",
      correct = false
    )
  }

  // 11. Final Wrap-Up
  text("final_wrapup_title") {
    style = TextStyle.Heading
    text = "Summary"
  }
  text("final_wrapup1") {
    text = """
Time complexity helps us understand performance. Big O notation is a tool to compare algorithms.
It’s about how fast they grow, not exact speeds. 
        """.trimIndent()
  }
  text("final_wrapup2") {
    text = """
Fast binary search can save you time, unlike slow brute-forcing a PIN digit by digit. Now you know why time complexity matters! 
        """.trimIndent()
    style = TextStyle.BodySpacingMedium
  }
  text("final_wrapup3") {
    text = """
Joke: Binary search walks into a bar and starts looking in the middle. The bartender says, "Find anything yet?" 
        """.trimIndent()
    style = TextStyle.BodySpacingMedium
  }
}