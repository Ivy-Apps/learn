package ui.screen.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.ScreenType.*
import component.button.PrimaryButton
import component.screenType
import component.text.Title
import ui.screen.settings.SettingsViewEvent
import ui.screen.settings.SettingsViewState
import ui.theme.colorsExt

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
                    onClick = {
                        onEvent(SettingsViewEvent.OnPremiumClick)
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
            item(key = "app") {
                Title("App")
                Spacer(Modifier.height(12.dp))
                NeutralButton(
                    modifier = Modifier.background(color = MaterialTheme.colorsExt.backgroundVariant),
                    onClick = {
                        onEvent(SettingsViewEvent.OnSoundsEnabledChange(!viewState.soundsEnabled))
                    },
                    content = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Sounds")
                            Spacer(Modifier.weight(1f))
                            Switch(
                                checked = viewState.soundsEnabled,
                                onCheckedChange = {
                                    onEvent(SettingsViewEvent.OnSoundsEnabledChange(it))
                                },
                            )
                        }
                    }
                )
            }
            item(key = "privacy") {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Privacy",
                    onClick = {
                        onEvent(SettingsViewEvent.OnPrivacyClick)
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
            item(key = "delete-account") {
                DeleteAccountButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Delete account",
                    onClick = {
                        onEvent(SettingsViewEvent.OnDeleteAccountClick)
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
            item(key = "terms-privacy") {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.clickable {
                            onEvent(SettingsViewEvent.OnTermsOfUseClick)
                        },
                        text = "Terms of use",
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        modifier = Modifier.clickable {
                            onEvent(SettingsViewEvent.OnPrivacyPolicyClick)
                        },
                        text = "Privacy policy",
                    )
                }
            }
        }
    }
}

@Composable
private fun NeutralButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorsExt.backgroundVariant,
            contentColor = MaterialTheme.colorsExt.onBackgroundVariant
        )
    ) {
        content()
    }
}

@Composable
private fun DeleteAccountButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.error,
            contentColor = MaterialTheme.colors.onError
        )
    ) {
        Text(text)
    }
}