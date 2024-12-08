package ivy.learn.data.repository

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.di.Di
import ivy.learn.domain.model.LessonProgress
import ivy.learn.testsupport.ServerTest
import ivy.learn.testsupport.data.repository.LessonProgressFixtures
import ivy.model.*
import org.junit.Test

class LessonProgressRepositoryTest : ServerTest() {

    @Test
    fun `upsert - insert new record`() = beTest {
        // Given
        val auth = registerUser()
        val repository = Di.get<LessonProgressRepository>()
        val progress = LessonProgress(
            userId = auth.user.id,
            courseId = CourseId("1"),
            lessonId = LessonId("2"),
            state = LessonProgressFixtures.state(
                selectedAnswers = mapOf(
                    LessonItemId("1") to setOf(AnswerId("a"), AnswerId("b"))
                ),
                openAnswersInput = mapOf(
                    LessonItemId("2") to "3.14",
                ),
                chosen = mapOf(
                    LessonItemId("3") to ChoiceOptionId("right"),
                ),
                answered = setOf(LessonItemId("1"), LessonItemId("2"), LessonItemId("3")),
                completed = setOf(LessonItemId("4"))
            )
        )

        // When
        val res = repository.upsert(progress)

        // Then
        res.shouldBeRight() shouldBe progress
        val recovered = repository.findBy(progress.userId, progress.courseId, progress.lessonId)
        recovered.shouldBeRight() shouldBe progress
    }
}