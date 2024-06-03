package ivy.learn.data.cms.course.demo

import ivy.content.CourseImage
import ivy.content.LessonImage
import ivy.learn.data.cms.dsl.CourseDsl

object DemoCourse : CourseDsl({
    name = "Demo Course"
    tagline = "Demo course for testing purposes."
    imageUrl = CourseImage
    lesson(
        name = "Demo Lesson",
        tagline = "Demo lesson for testing purposes.",
        imageUrl = LessonImage,
    )
})