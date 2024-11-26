package ui.screen.settings.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.ScreenType.*
import component.button.PrimaryButton
import component.screenType
import component.text.SubTitle
import component.text.Title
import ui.screen.settings.SettingsViewEvent
import ui.screen.settings.SettingsViewState

@Composable
fun SettingsContent(
    viewState: SettingsViewState,
    onEvent: (SettingsViewEvent) -> Unit
) {
    LearnScaffold(
        backButton = BackButton(onBackClick = {
            onEvent(SettingsViewEvent.OnBackClick)
        }),
        title = "Settings"
    ) { contentPadding ->
        val horizontalPadding = when (screenType()) {
            Mobile -> 8.dp
            Tablet -> 16.dp
            Desktop -> 16.dp
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                top = 32.dp,
                bottom = 48.dp,
                start = horizontalPadding,
                end = horizontalPadding,
            )
        ) {
            item(key = "premium") {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Premium",
                    onClick = {}
                )
                Spacer(Modifier.height(16.dp))
            }
            item(key = "app") {
                Title("App")
                Spacer(Modifier.height(12.dp))
                Row {
                    SubTitle(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Sounds",
                    )
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = viewState.soundsEnabled,
                        onCheckedChange = {
                            onEvent(SettingsViewEvent.OnSoundsEnabledChange(it))
                        },
                    )
                }
            }
        }
    }
}