package ivy.learn.data.di

import ivy.di.Di
import ivy.di.Di.register
import ivy.learn.data.database.Database
import ivy.learn.data.repository.CoursesRepository
import ivy.learn.data.repository.LessonsRepository
import ivy.learn.data.repository.TopicsRepository
import ivy.learn.data.source.LessonContentDataSource

object DataModule : Di.Module {
    override fun init() = Di.appScope {
        register { Database() }
        register { LessonContentDataSource(Di.get(), Di.get()) }
        register { LessonsRepository(Di.get()) }
        register { CoursesRepository() }
        register { TopicsRepository() }
    }
}