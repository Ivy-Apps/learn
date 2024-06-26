package ivy.learn.data.cms.course

import ivy.learn.data.cms.course.android.AndroidArchitecture
import ivy.learn.data.cms.course.android.ScreenArchitecture
import ivy.learn.data.cms.course.demo.DemoCourse
import ivy.learn.data.cms.course.kotlin.DataModeling
import ivy.learn.data.cms.course.kotlin.ErrorHandling
import ivy.learn.data.cms.course.kotlin.KotlinDSLs
import ivy.learn.data.cms.course.programming.DataStructures
import ivy.learn.data.cms.course.programming.FunctionalProgramming
import ivy.learn.data.cms.course.programming.ObjectOrientedProgramming
import ivy.learn.data.cms.course.programming.ProgrammingFundamentals
import ivy.learn.data.cms.dsl.CoursesDsl

object CoursesContent : CoursesDsl({
    course(DemoCourse)
    course(ProgrammingFundamentals)
    course(DataStructures)
    course(FunctionalProgramming)
    course(ObjectOrientedProgramming)

    course(DataModeling)
    course(ErrorHandling)
    course(KotlinDSLs)

    course(AndroidArchitecture)
    course(ScreenArchitecture)
})