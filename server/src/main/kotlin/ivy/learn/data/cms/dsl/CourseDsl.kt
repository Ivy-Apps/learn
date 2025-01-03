package ivy.learn.data.cms.dsl

import ivy.content.EmptyContent
import ivy.learn.*
import ivy.learn.dsl.LearnCmsDsl

abstract class CoursesDsl(
  builder: CoursesScope.() -> Unit
) {
  private val scope = CoursesScopeImpl().apply(builder)

  val courses: List<Course> = scope.courses
  val coursesMap: Map<CourseId, Course> = courses.associateBy(Course::id)
  val lessonsMap: Map<LessonId, Lesson> = scope.lessons
}

class CoursesScopeImpl : CoursesScope {
  val courses = mutableListOf<Course>()
  val lessons = mutableMapOf<LessonId, Lesson>()

  override fun course(courseDsl: CourseDsl) {
    courses.add(courseDsl.course)
    lessons.putAll(courseDsl.lessonsMap)
  }
}

interface CoursesScope {
  @LearnCmsDsl
  fun course(courseDsl: CourseDsl)
}

@LearnCmsDsl
abstract class CourseDsl(
  builder: CourseScope.() -> Unit
) {
  private val scope = CourseScopeImpl().apply(builder)

  val course: Course
    get() = scope.buildCourse()

  val lessons: List<Lesson> = scope.lessons
  val lessonsMap: Map<LessonId, Lesson> = lessons.associateBy(Lesson::id)
}

class CourseScopeImpl : CourseScope {
  override lateinit var name: String
  override lateinit var tagline: String
  override lateinit var imageUrl: String

  val lessons = mutableListOf<Lesson>()

  override fun lesson(
    name: String,
    tagline: String,
    imageUrl: String,
    id: String,
  ) {
    lessons.add(
      Lesson(
        id = LessonId(id),
        name = name,
        tagline = tagline,
        image = ImageUrl(imageUrl),
        content = EmptyContent,
        completed = false,
      )
    )
  }

  fun buildCourse(): Course {
    return Course(
      id = CourseId(nameToId(name)),
      name = name,
      tagline = tagline,
      image = ImageUrl(imageUrl),
      lessons = lessons.map(Lesson::id)
    )
  }
}

interface CourseScope {
  var name: String
  var tagline: String
  var imageUrl: String

  @LearnCmsDsl
  fun lesson(
    name: String,
    tagline: String,
    imageUrl: String,
    id: String = nameToId(name),
  )
}