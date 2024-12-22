package ivy.learn.data.cms.course.algorithms

import ivy.learn.data.cms.dsl.CourseDsl

object AlgorithmFoundations : CourseDsl({
  name = "Algorithm Foundations"
  tagline = "Demystify space & time complexity, Big O... The first step to becoming an algorithm master."
  imageUrl = "https://i.ibb.co/566dj34/algo-fundamentals.webp"
  lesson(
    name = "Time Complexity: What and Why?",
    tagline = "An introduction to Big O and its real-world importance.",
    imageUrl = "https://i.ibb.co/njy7FxD/time-complexity-1.webp",
  )
  lesson(
    name = "Time Complexity: Types",
    tagline = "O(1), O(log(n)), O(n), O(n * log(n)), O(n^2), O(2^n), O(n!),... and how to find them!?",
    imageUrl = "https://i.ibb.co/V2rg0mY/time-complexity-types.webp",
  )
})