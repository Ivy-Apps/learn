package data.di

import data.*
import data.fake.FakeCourseRepository
import data.fake.FakeLessonRepository
import data.fake.FakeTopicsRepository
import data.lesson.LessonRepository
import data.lesson.LessonRepositoryImpl
import data.lesson.mapper.LessonMapper
import data.storage.LocalStorage
import data.storage.localStorage
import di.bindWithFake
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire

object DataModule : Di.Module {
    override fun init() = Di.appScope {
        autoWire(::TopicsRepositoryImpl)
        autoWire(::FakeTopicsRepository)
        bindWithFake<TopicsRepository, TopicsRepositoryImpl, FakeTopicsRepository>()
        autoWire(::CourseRepositoryImpl)
        autoWire(::FakeCourseRepository)
        bindWithFake<CourseRepository, CourseRepositoryImpl, FakeCourseRepository>()
        autoWire(::LessonRepositoryImpl)
        autoWire(::FakeLessonRepository)
        bindWithFake<LessonRepository, LessonRepositoryImpl, FakeLessonRepository>()
        register<LocalStorage> { localStorage() }
        autoWire(::UserRepository)
        autoWire(::AnalyticsRepository)
        autoWire(::LessonMapper)
        autoWire(::SoundRepository)
    }
}