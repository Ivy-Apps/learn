package ivy.learn.data.cms

import ivy.learn.data.cms.course.GeneralProgramming
import ivy.learn.data.cms.dsl.TopicsDsl

object TopicsContent : TopicsDsl({
    topic("General Programming") {
        course(GeneralProgramming)
    }
})