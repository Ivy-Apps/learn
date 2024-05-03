package ui.screen.home

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import ui.ComposeViewModel
import ui.navigation.Navigation

class HomeViewModel(
    private val navigation: Navigation
) : ComposeViewModel<HomeViewState, HomeViewEvent> {
    @Composable
    override fun viewState(): HomeViewState {
        return HomeViewState(
            items = persistentListOf(
                HomeItemViewState.Section(title = "Android"),
                HomeItemViewState.Course(
                    id = "123",
                    imageUrl = "https://media.istockphoto.com/id/1483105195/photo/" +
                            "industrial-style-of-dark-interior-design-3d-render.jpg?s=1024x1024&" +
                            "w=is&k=20&c=Go89XVVSsi53IH_nPZ2yMRLXhrz051zKh379-RrBAkQ=",
                    name = "Kotlin fundamentals",
                    tagline = "Learn the advanced concepts in Kotlin"
                ),
                HomeItemViewState.Course(
                    id = "567",
                    imageUrl = "https://media.istockphoto.com/id/1483105195/photo/" +
                            "industrial-style-of-dark-interior-design-3d-render.jpg?s=1024x1024&" +
                            "w=is&k=20&c=Go89XVVSsi53IH_nPZ2yMRLXhrz051zKh379-RrBAkQ=",
                    name = "Kotlin fundamentals",
                    tagline = "Learn the advanced concepts in Kotlin"
                ),
                HomeItemViewState.Section(title = "Programming"),
                HomeItemViewState.Course(
                    id = "234",
                    imageUrl = "https://cdn.pixabay.com/photo/2023/03/03/14/16/light-7827771_128" +
                            "0.jpg",
                    name = "General programming",
                    tagline = "Learn programming by thinking. Get in depth knowledge of how " +
                            "complicated systems work."
                )
            )
        )
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            HomeViewEvent.OnBackClick -> handleBackClick()
            is HomeViewEvent.OnCourseClick -> handleCourseClick(event)
        }
    }

    private fun handleBackClick() {
        navigation.back()
    }

    private fun handleCourseClick(event: HomeViewEvent.OnCourseClick) {
        // TODO - go to course
    }
}