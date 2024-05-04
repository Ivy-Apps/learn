package ivy.learn.data.cms.course

import ivy.learn.data.cms.course.programming.ProgrammingBasics
import ivy.learn.data.cms.dsl.CoursesDsl

object CoursesContent : CoursesDsl({
    course(ProgrammingBasics)
})