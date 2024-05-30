package ivy.learn.data.cms

import ivy.learn.data.cms.course.android.AndroidArchitecture
import ivy.learn.data.cms.course.android.ScreenArchitecture
import ivy.learn.data.cms.course.demo.DemoCourse
import ivy.learn.data.cms.course.kotlin.DataModeling
import ivy.learn.data.cms.course.kotlin.ErrorHandling
import ivy.learn.data.cms.course.kotlin.KotlinDSLs
import ivy.learn.data.cms.course.programming.*
import ivy.learn.data.cms.dsl.TopicsDsl

object TopicsContent : TopicsDsl({
    topic("Demo") {
        course(DemoCourse)
    }
    topic("Programming") {
        course(ProgrammingBasics)
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
})