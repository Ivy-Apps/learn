package data.di

import data.CourseRepository
import data.LessonRepository
import data.TopicsRepository
import ivy.di.Di
import ivy.di.Di.register

object DataModule : Di.Module {
    override fun init() = Di.appScope {
        register { TopicsRepository(Di.get(), Di.get()) }
        register { CourseRepository(Di.get(), Di.get()) }
        register { LessonRepository(Di.get(), Di.get()) }
    }
}