package data.di

import AppConfiguration
import data.CourseRepository
import data.LessonRepository
import data.LessonRepositoryImpl
import data.TopicsRepository
import data.fake.FakeLessonRepository
import di.bindWithFake
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire

object DataModule : Di.Module {
    override fun init() = Di.appScope {
        register { TopicsRepository(Di.get(), Di.get()) }
        register { CourseRepository(Di.get(), Di.get()) }
        autoWire(::LessonRepositoryImpl)
        autoWire(::FakeLessonRepository)
        register<LessonRepository> {
            if (Di.get<AppConfiguration>().fakesEnabled) {
                Di.get<FakeLessonRepository>()
            } else {
                Di.get<LessonRepositoryImpl>()
            }
        }
        bindWithFake<LessonRepository, LessonRepositoryImpl, FakeLessonRepository>()
    }
}