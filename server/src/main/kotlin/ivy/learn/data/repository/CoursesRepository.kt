package ivy.learn.data.repository

import ivy.learn.data.cms.course.CoursesContent
import ivy.model.Course
import ivy.model.CourseId

class CoursesRepository {
    fun fetchCourses(): List<Course> = CoursesContent.courses

    fun fetchCourseById(id: CourseId): Course? = CoursesContent.courses.find { it.id == id }
}