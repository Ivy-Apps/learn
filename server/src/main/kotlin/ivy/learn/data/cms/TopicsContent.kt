package ivy.learn.data.cms

import ivy.learn.data.cms.course.algorithms.AlgorithmFoundations
import ivy.learn.data.cms.dsl.TopicsDsl

object TopicsContent : TopicsDsl({
  topic("Computer Science") {
    course(AlgorithmFoundations)
  }
})