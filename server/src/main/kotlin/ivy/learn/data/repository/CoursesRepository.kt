package ivy.learn.data.repository

import ivy.learn.Course
import ivy.learn.CourseId
import ivy.learn.data.cms.course.CoursesContent

class CoursesRepository {
    fun fetchCourses(): List<Course> = CoursesContent.courses

    fun fetchCourseById(id: CourseId): Course? = CoursesContent.courses.find { it.id == id }
}