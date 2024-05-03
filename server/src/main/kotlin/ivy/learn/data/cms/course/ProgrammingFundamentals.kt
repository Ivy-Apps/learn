package ivy.learn.data.cms.course

import ivy.learn.data.cms.util.EmptyContent
import ivy.model.*

object ProgrammingFundamentals {
    val Programming101 = Lesson(
        id = LessonId("programming-fundamentals-101"),
        name = "Programming Fundamentals 101",
        tagline = "Learn the basics of programming",
        content = EmptyContent
    )

    val course = Course(
        id = CourseId("general-programming"),
        name = "General Programming",
        tagline = "Learn the basics of programming",
        icon = ImageUrl("tbd"),
        lessons = listOf(
            Programming101.id
        )
    )
}