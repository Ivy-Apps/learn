package ivy.learn.data.di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.DiModule
import ivy.learn.data.database.Database
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.data.source.LessonDataSource

object DataModule : DiModule {
    override fun init() = Di.appScope {
        register { Database() }
        register { LessonDataSource(Di.get(), Di.get()) }
        register { LessonsRepository(Di.get()) }
    }
}