package ui.screen.settings.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.ScreenType.*
import component.button.PrimaryButton
import component.screenType
import ui.screen.settings.SettingsViewEvent

@Composable
fun SettingsContent(
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
            PrimaryButton(text = "Test", onClick = {})
        }
    }
}