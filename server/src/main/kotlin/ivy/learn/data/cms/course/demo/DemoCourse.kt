package ivy.learn.data.cms.course.demo

import ivy.learn.data.cms.dsl.CourseDsl
import ivy.learn.data.cms.dsl.CourseImage
import ivy.learn.data.cms.dsl.LessonImage

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