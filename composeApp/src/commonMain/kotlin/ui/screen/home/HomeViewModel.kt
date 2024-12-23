package ui.screen.home

import androidx.compose.runtime.*
import arrow.core.identity
import data.TopicsRepository
import domain.analytics.Analytics
import domain.analytics.Param
import domain.analytics.Source
import ivy.data.source.model.TopicsResponse
import kotlinx.collections.immutable.persistentListOf
import navigation.Navigation
import ui.ComposeViewModel
import ui.screen.course.CourseScreen
import ui.screen.home.mapper.HomeViewStateMapper
import ui.screen.settings.SettingsScreen

class HomeViewModel(
    private val navigation: Navigation,
    private val repository: TopicsRepository,
    private val mapper: HomeViewStateMapper,
    private val analytics: Analytics,
) : ComposeViewModel<HomeViewState, HomeViewEvent> {

    private var topicsResponse by mutableStateOf<TopicsResponse?>(null)

    @Composable
    override fun viewState(): HomeViewState {
        LaunchedEffect(Unit) {
            topicsResponse = repository.fetchTopics().fold(
                ifLeft = { null },
                ifRight = ::identity
            )
            logEvent("view")
        }
        return HomeViewState(
            items = with(mapper) { topicsResponse?.toViewState() }
                ?: persistentListOf()
        )
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnCourseClick -> handleCourseClick(event)
            HomeViewEvent.OnSettingsClick -> handleSettingsClick()
        }
    }

    private fun handleSettingsClick() {
        navigation.navigateTo(SettingsScreen())
    }

    private fun handleCourseClick(event: HomeViewEvent.OnCourseClick) {
        logEvent(
            event = "click_course",
            params = mapOf(
                Param.CourseId to event.id.value,
                Param.CourseName to event.name,
            )
        )
        navigation.navigateTo(CourseScreen(courseId = event.id, courseName = event.name))
    }

    private fun logEvent(
        event: String,
        params: Map<String, String>? = null,
    ) {
        analytics.logEvent(
            source = Source.Home,
            event = event,
            params = params,
        )
    }
}