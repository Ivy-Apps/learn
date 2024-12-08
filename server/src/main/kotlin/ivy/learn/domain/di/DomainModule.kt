package ivy.learn.domain.di

import ivy.di.Di
import ivy.di.autowire.autoWire
import ivy.learn.domain.AnalyticsService
import ivy.learn.domain.CourseService
import ivy.learn.domain.auth.AuthenticationService
import ivy.learn.domain.auth.GoogleOAuthUseCase
import ivy.learn.domain.lesson.LessonService

object DomainModule : Di.Module {
    override fun init() = Di.appScope {
        autoWire(::AuthenticationService)
        autoWire(::GoogleOAuthUseCase)
        autoWire(::AnalyticsService)
        autoWire(::LessonService)
        autoWire(::CourseService)
    }
}