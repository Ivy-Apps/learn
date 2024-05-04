package ivy.learn.data.cms.course

import ivy.learn.data.cms.course.programming.*
import ivy.learn.data.cms.dsl.CoursesDsl

object CoursesContent : CoursesDsl({
    course(ProgrammingBasics)
    course(ProgrammingFundamentals)
    course(DataStructures)
    course(FunctionalProgramming)
    course(ObjectOrientedProgramming)
})