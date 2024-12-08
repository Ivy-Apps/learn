package ivy.learn.data.repository

import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ivy.di.Di
import ivy.learn.domain.model.LessonProgress
import ivy.learn.domain.model.UserId
import ivy.learn.testsupport.ServerTest
import ivy.learn.testsupport.data.repository.LessonProgressFixtures
import ivy.model.*
import org.junit.Test
import java.util.*

class LessonProgressRepositoryTest : ServerTest() {

    @Test
    fun `upsert - inserts new record`() = beTest {
        // Given
        val auth = registerUser()
        val repository = Di.get<LessonProgressRepository>()
        val progress = LessonProgress(
            userId = auth.user.id,
            courseId = CourseId("1"),
            lessonId = LessonId("2"),
            state = FullState,
        )

        // When
        val res = repository.upsert(progress)

        // Then
        res.shouldBeRight() shouldBe progress
        val recovered = repository.findBy(progress.userId, progress.courseId, progress.lessonId)
        recovered.shouldBeRight() shouldBe progress
    }

    @Test
    fun `upsert - updates existing record`() = beTest {
        // Given
        val auth = registerUser()
        val repository = Di.get<LessonProgressRepository>()
        val progress = LessonProgress(
            userId = auth.user.id,
            courseId = CourseId("1"),
            lessonId = LessonId("2"),
            state = FullState,
        )
        repository.upsert(progress).shouldBeRight() shouldBe progress
        val newProgress = progress.copy(
            state = FullState.copy(
                openAnswersInput = mapOf(
                    LessonItemId("100") to UUID.randomUUID().toString(),
                )
            )
        )

        // When
        val res = repository.upsert(newProgress)

        // Then
        res.shouldBeRight() shouldBe newProgress
        val recovered = repository.findBy(progress.userId, progress.courseId, progress.lessonId)
        recovered.shouldBeRight() shouldBe newProgress
    }

    @Test
    fun `upsert - inserts multiple items`() = beTest {
        val authA = registerUser()
        val authB = registerUser()
        val repository = Di.get<LessonProgressRepository>()
        val progressA = LessonProgress(
            userId = authA.user.id,
            courseId = CourseId("1"),
            lessonId = LessonId("2"),
            state = FullState,
        )
        val progressB = LessonProgress(
            userId = authB.user.id,
            courseId = CourseId("1"),
            lessonId = LessonId("2"),
            state = LessonProgressFixtures.state(),
        )

        // When
        repository.upsert(progressA)
        repository.upsert(progressB)

        // Then
        val recoveredA = repository.findBy(progressA.userId, progressA.courseId, progressA.lessonId)
        val recoveredB = repository.findBy(progressB.userId, progressB.courseId, progressB.lessonId)
        recoveredA.shouldBeRight() shouldBe progressA
        recoveredB.shouldBeRight() shouldBe progressB
    }

    @Test
    fun `findBy - not existing item returns null`() = beTest {
        // Given
        val repository = Di.get<LessonProgressRepository>()

        // When
        val res = repository.findBy(
            userId = UserId(UUID.randomUUID()),
            courseId = CourseId("c"),
            lessonId = LessonId("l")
        )

        // Then
        res.shouldBeRight() shouldBe null
    }

    companion object {
        val FullState = LessonProgressFixtures.state(
            selectedAnswers = mapOf(
                LessonItemId("1") to setOf(AnswerId("a"), AnswerId("b")),
                LessonItemId("1.1") to setOf(AnswerId("d"))
            ),
            openAnswersInput = mapOf(
                LessonItemId("2") to "3.14",
                LessonItemId("30") to "42",
            ),
            chosen = mapOf(
                LessonItemId("3") to ChoiceOptionId("right"),
                LessonItemId("4") to ChoiceOptionId("left"),
            ),
            answered = setOf(LessonItemId("1"), LessonItemId("2"), LessonItemId("3")),
            completed = setOf(LessonItemId("4"), LessonItemId("30")),
        )
    }
}