package ivy.learn.data.cms

import ivy.learn.data.cms.course.programming.ProgrammingBasics
import ivy.learn.data.cms.dsl.TopicsDsl

object TopicsContent : TopicsDsl({
    topic("Programming") {
        course(ProgrammingBasics)
    }
    topic("Functional Programming") {

    }
    topic("Object Oriented Programming") {

    }
    topic("Kotlin") {

    }
    topic("Compose") {

    }
})