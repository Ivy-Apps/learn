package ivy.model.dsl

import ivy.model.CourseId
import ivy.model.Lesson
import ivy.model.LessonId
import ivy.model.LessonItemId

fun lesson(
    course: CourseId,
    id: LessonId,
    name: String,
    tagline: String,
    build: LessonScope.() -> Unit
): Lesson {
    return Lesson(
        id = id,
        name = name,
        tagline = tagline,
        rootItem = LessonItemId(value = ""),
        items = mapOf()
    )
}

interface LessonScope {

}

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class LessonDsl

fun test() {
    lesson(
        course = CourseId("programming_fundamentals_101"),
        id = LessonId("what_is_programming"),
        name = "What is programming?",
        tagline = "Learn the basics of programming."
    ) {

    }
}