package ivy.learn.domain.analytics

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import ivy.data.source.model.KpisResponse
import ivy.di.Di
import ivy.learn.api.common.model.ServerError
import ivy.learn.domain.analytics.kpi.*
import ivy.learn.domain.auth.AuthService
import ivy.model.auth.SessionToken

class KpiService(
  private val authService: AuthService,
  private val funnel: KpiFunnel,
) {
  companion object {
    val ALLOWED_USERS = setOf(
      "iliyan.germanov971@gmail.com",
      "nicole.georgievva@gmail.com",
    )
  }

  suspend fun computeKpis(
    sessionToken: SessionToken,
  ): Either<ServerError, KpisResponse> = either {
    val user = authService.getUser(sessionToken).bind()
    ensure(user.email in ALLOWED_USERS) {
      ServerError.Unauthorized
    }


    KpisResponse(
      funnel = funnel.compute(),
      kpis = listOf(
        Di.get<IntroViewsKpi>(),
        Di.get<IntroLearnMoreClicksKpi>(),
        Di.get<UsersCountKpi>(),
        Di.get<CoursesViewKpi>(),
        Di.get<LessonViewsKpi>(),
        Di.get<AvgLessonViewsPerUserKpi>(),
        Di.get<UsersCompletedLessonKpi>(),
        Di.get<ActiveUsersAvgCompletedLessonsKpi>(),
        Di.get<TopLessonsByDistinctViewsKpi>(),
        Di.get<TopLessonsByDistinctCompletionsKpi>(),
        Di.get<TopLessonsByDistinctCompletionRateKpi>(),
        Di.get<TopCoursesByDistinctViewsKpi>(),
      ).map { it.compute() },
    )
  }
}


