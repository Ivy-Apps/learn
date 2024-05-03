package ui.screen.home

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class HomeViewState(
    val items: ImmutableList<HomeItemViewState>
)

@Immutable
sealed interface HomeItemViewState {
    data class Section(val title: String) : HomeItemViewState
    data class Course(
        val id: String,
        val imageUrl: String,
        val name: String,
        val tagLine: String
    ) : HomeItemViewState
}

sealed interface HomeViewEvent {
    data object OnBackClick : HomeViewEvent
    data object OnColorsDemoClick : HomeViewEvent
}