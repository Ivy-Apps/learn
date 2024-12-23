package ivy.learn.data.cms.course.algorithms

import ivy.learn.data.cms.dsl.CourseDsl

object AlgorithmFoundations : CourseDsl({
  name = "Algorithm Foundations"
  tagline = "Demystify space & time complexity, Big O... The first step to becoming an algorithm master."
  imageUrl = "https://i.ibb.co/ctdCRzy/algorithm-foundation.webp"
  lesson(
    name = "Time Complexity: What and Why?",
    tagline = "An introduction to Big O and its real-world importance.",
    imageUrl = "https://i.ibb.co/0Qrzpss/time-complexity.webp",
  )
  lesson(
    name = "Time Complexity: Types",
    tagline = "O(1), O(log(n)), O(n), O(n * log(n)), O(n^2), O(2^n), O(n!),... and how to find them!?",
    imageUrl = "https://i.ibb.co/hWPHBcW/time-complexity-types.webp",
  )
  lesson(
    id = "space-complexity",
    name = "Space Complexity",
    tagline = "Understand how memory scales—no fluff, just the essentials",
    imageUrl = "https://i.ibb.co/x6bqy32/space-complexity-banner.webp"
  )

  lesson(
    id = "big-o-advanced",
    name = "Big O: Advanced",
    tagline = "Take your algorithm analysis to the next level",
    imageUrl = "https://i.ibb.co/XSJHyyp/big-o-advanced.webp"
  )

  lesson(
    id = "space-time-complexity-practice",
    name = "Space-time Complexity: Practice",
    tagline = "Sharpen your skills with real-world complexity drills",
    imageUrl = "https://i.ibb.co/gJTFtGt/space-time-complexity-practice.webp"
  )
})