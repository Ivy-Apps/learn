package ivy.learn.data.cms

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
import ivy.learn.data.cms.dsl.TopicsDsl

object TopicsContent : TopicsDsl({
    topic("Software Engineering") {
        course(ProgrammingFundamentals)
        course(DataStructures)
        course(FunctionalProgramming)
        course(ObjectOrientedProgramming)
    }
    topic("Kotlin") {
        course(DataModeling)
        course(ErrorHandling)
        course(KotlinDSLs)
    }
    topic("Android") {
        course(AndroidArchitecture)
        course(ScreenArchitecture)
    }
    topic("Demo") {
        course(DemoCourse)
    }
})