package data.di

import data.CourseRepository
import data.TopicsRepository
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.DiModule

object DataModule : DiModule {
    override fun init() = Di.appScope {
        register { TopicsRepository(Di.get(), Di.get()) }
        register { CourseRepository(Di.get(), Di.get()) }
    }
}