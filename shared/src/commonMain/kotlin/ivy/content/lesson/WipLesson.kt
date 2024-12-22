import ivy.model.TextStyle
import ivy.model.dsl.codeBuilder
import ivy.model.dsl.codeExample
import ivy.model.dsl.lessonContent
import ivy.model.dsl.textBuilder

fun wipLesson() = lessonContent {
  text("introduction") {
    text = "Welcome to Time Complexity: Big O Notation!"
    style = TextStyle.Heading
  }

  text("why_time_complexity_matters", next = "big_o_basics") {
    text = textBuilder {
      line("Imagine you have a program that processes data.")
      line("How quickly it runs depends on how well it's designed.")
      bullet("Time complexity helps us understand and compare algorithm efficiency.")
      bullet("It predicts how processing time increases with input size.")
    }
    style = TextStyle.BodySpacingMedium
  }

  text("big_o_basics") {
    text = textBuilder {
      line("Big O notation describes how the runtime grows as input size grows.")
      bullet("O(1): Constant time — doesn’t grow with input size.")
      bullet("O(n): Linear time — grows proportionally with input size.")
      bullet("O(logn): Logarithmic time — grows slower than input size.")
      bullet("O(n^2): Quadratic time — increases significantly as input size doubles, typically seen in nested loops.")
    }
    style = TextStyle.BodySpacingMedium
  }

  codeExample("o1_example") {
    text = codeBuilder {
      line("// O(1): Constant Time Example")
      line("fun getFirstElement(array: List<Int>): Int {")
      line("    return array[0]")
      line("}")
    }
  }

  codeExample("on_example") {
    text = codeBuilder {
      line("// O(n): Linear Time Example")
      line("fun printAllElements(array: List<Int>) {")
      line("    for (element in array) {")
      line("        println(element)")
      line("    }")
      line("}")
    }
  }

  question("complexity_question") {
    question = "What is the time complexity of searching for a value in a sorted list using binary search?"
    clarification = "Consider how the search space reduces with each step."
    answer(text = "O(1)", explanation = "Incorrect, O(1) represents constant time.")
    answer(text = "O(n)", explanation = "Not correct, this applies to linear search.")
    answer(text = "O(logn)", correct = true, explanation = "Correct! Each step halves the search space.")
  }

  text("detailed_breakdown") {
    text = textBuilder {
      line("Detailed Breakdown of Key Complexities:")
      bullet("O(1): No matter the input size, time remains the same.")
      bullet("O(n): Time grows linearly with input size.")
      bullet("O(logn): Time grows slowly as input doubles.")
      bullet("O(n^2): Time grows quadratically, typically resulting from nested loops or comparisons.")
    }
    style = TextStyle.BodySpacingLarge
  }

  image("complexity_chart") {
    imageUrl = "https://example.com/big_o_chart.png"
  }

  question("real_world_applications") {
    question = "Which time complexity is best for real-time applications requiring fast responses?"
    clarification = "Think about applications like gaming or financial systems."
    answer(text = "O(1)", correct = true, explanation = "Correct! Constant time guarantees immediate responses.")
    answer(text = "O(n)", explanation = "Not ideal; linear time can slow down as input grows.")
    answer(text = "O(n^2)", explanation = "Incorrect; this is too slow for real-time scenarios.")
  }

  text("reflection") {
    text = textBuilder {
      line("Reflection:")
      bullet("What complexity do your programs usually have?")
      bullet("How can you optimize algorithms for better performance?")
    }
    style = TextStyle.BodySpacingMedium
  }

  text("summary") {
    text = textBuilder {
      line("Key Takeaways:")
      bullet("Big O helps evaluate algorithm efficiency.")
      bullet("Understand and identify common complexities: O(1), O(n), O(logn), O(n^2).")
      bullet("Always aim for optimal time complexity in your programs.")
    }
    style = TextStyle.BodySpacingLarge
  }
}
