package ivy.learn.data.cms

import ivy.learn.data.cms.course.programming.*
import ivy.learn.data.cms.dsl.TopicsDsl

object TopicsContent : TopicsDsl({
    topic("Programming") {
        course(ProgrammingBasics)
        course(ProgrammingFundamentals)
        course(DataStructures)
        course(FunctionalProgramming)
        course(ObjectOrientedProgramming)
    }
    topic("Kotlin") {

    }
    topic("Compose") {

    }
})