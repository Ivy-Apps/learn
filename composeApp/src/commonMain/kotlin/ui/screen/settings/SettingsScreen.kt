package ui.screen.settings

import androidx.compose.runtime.Composable
import ivy.di.Di
import ivy.di.Di.register
import ivy.model.CourseId
import ui.navigation.Screen
import ui.screen.course.composable.CourseContent
import ui.screen.course.mapper.CourseViewStateMapper
import ui.screen.settings.content.settingsContent

class SettingsScreen : Screen() {
    override val path: String = "settings"

    override fun onDi(): Di.Scope.() -> Unit = {
        register {
            SettingsViewModel(navigation = Di.get())
        }
    }

    private val viewModel: SettingsViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        settingsContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}