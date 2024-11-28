package ui.screen.home

import androidx.compose.runtime.*
import arrow.core.identity
import data.TopicsRepository
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
) : ComposeViewModel<HomeViewState, HomeViewEvent> {

    private var topicsResponse by mutableStateOf<TopicsResponse?>(null)

    @Composable
    override fun viewState(): HomeViewState {
        LaunchedEffect(Unit) {
            topicsResponse = repository.fetchTopics().fold(
                ifLeft = { null },
                ifRight = ::identity
            )
        }
        return HomeViewState(
            items = with(mapper) { topicsResponse?.toViewState() }
                ?: persistentListOf()
        )
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            HomeViewEvent.OnBackClick -> handleBackClick()
            is HomeViewEvent.OnCourseClick -> handleCourseClick(event)
            HomeViewEvent.OnSettingsClick -> handleSettingsClick()
        }
    }

    private fun handleBackClick() {
        navigation.navigateBack()
    }

    private fun handleSettingsClick() {
        navigation.navigateTo(SettingsScreen())
    }

    private fun handleCourseClick(event: HomeViewEvent.OnCourseClick) {
        navigation.navigateTo(CourseScreen(courseId = event.id, courseName = event.name))
    }
}