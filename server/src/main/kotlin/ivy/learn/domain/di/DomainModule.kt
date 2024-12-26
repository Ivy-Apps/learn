package ivy.learn.domain.di

import ivy.di.Di
import ivy.di.autowire.autoWire
import ivy.learn.domain.CourseService
import ivy.learn.domain.LessonService
import ivy.learn.domain.MetricsService
import ivy.learn.domain.TopicsService
import ivy.learn.domain.analytics.Analytics
import ivy.learn.domain.analytics.AnalyticsService
import ivy.learn.domain.analytics.KpiService
import ivy.learn.domain.analytics.kpi.*
import ivy.learn.domain.auth.AuthService
import ivy.learn.domain.auth.GoogleOAuthUseCase

object DomainModule : Di.Module {
  override fun init() = Di.appScope {
    autoWire(::AuthService)
    autoWire(::GoogleOAuthUseCase)
    autoWire(::AnalyticsService)
    autoWire(::LessonService)
    autoWire(::CourseService)
    autoWire(::TopicsService)
    autoWire(::MetricsService)
    autoWire(::Analytics)
    autoWire(::KpiService)
    autoWire(::UsersCountKpi)
    autoWire(::IntroViewsKpi)
    autoWire(::IntroLearnMoreClicksKpi)
    autoWire(::CoursesViewKpi)
    autoWire(::LessonViewsKpi)
    autoWire(::UsersCompletedLessonKpi)
    autoWire(::ActiveUsersAvgCompletedLessonsKpi)
    autoWire(::TopCoursesByViewsKpi)
    autoWire(::TopLessonsByViewsKpi)
    autoWire(::TopLessonsByCompletionsKpi)
    autoWire(::AvgLessonViewsPerUserKpi)
  }
}