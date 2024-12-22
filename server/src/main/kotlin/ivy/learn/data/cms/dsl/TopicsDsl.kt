package ivy.learn.data.cms.dsl

import ivy.content.nameToId
import ivy.learn.CourseId
import ivy.learn.Topic
import ivy.learn.TopicId
import ivy.learn.dsl.LearnCmsDsl

abstract class TopicsDsl(builder: TopicsScope.() -> Unit) {
  private val scope = TopicsScopeImpl().apply(builder)

  val topics: List<Topic> = scope.topics
  val topicsMap: Map<TopicId, Topic> = topics.associateBy(Topic::id)
}

class TopicsScopeImpl : TopicsScope {
  val topics = mutableListOf<Topic>()

  override fun topic(name: String, builder: TopicsScope.CourseScope.() -> Unit) {
    val scope = TopicsScope.CourseScopeImpl().apply(builder)
    topics.add(
      Topic(
        id = TopicId(nameToId(name)),
        name = name,
        courses = scope.courses
      )
    )
  }
}

interface TopicsScope {
  @LearnCmsDsl
  fun topic(name: String, builder: CourseScope.() -> Unit)

  class CourseScopeImpl : CourseScope {
    val courses = mutableListOf<CourseId>()

    override fun course(courseDsl: CourseDsl) {
      courses.add(courseDsl.course.id)
    }

  }

  interface CourseScope {
    @LearnCmsDsl
    fun course(courseDsl: CourseDsl)
  }
}