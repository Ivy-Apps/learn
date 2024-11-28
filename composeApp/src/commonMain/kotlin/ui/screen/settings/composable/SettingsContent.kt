package ui.screen.settings.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
import component.button.PrimaryButton
import component.platformHorizontalPadding
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            val horizontalPadding = platformHorizontalPadding()
            LazyColumn(
                modifier = Modifier.widthIn(max = 800.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(
                    top = 32.dp,
                    bottom = 48.dp,
                    start = horizontalPadding,
                    end = horizontalPadding,
                )
            ) {
                premiumButton(
                    onPremiumClick = {
                        onEvent(SettingsViewEvent.OnPremiumClick)
                    }
                )
                appSettingsSection(
                    soundEnabled = viewState.soundEnabled,
                    onSoundEnabledChange = {
                        onEvent(SettingsViewEvent.OnSoundEnabledChange(it))
                    }
                )
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
}

private fun LazyListScope.appSettingsSection(
    soundEnabled: Boolean,
    onSoundEnabledChange: (Boolean) -> Unit,
) {
    item(key = "app") {
        Title("App")
        Spacer(Modifier.height(12.dp))
        SoundSwitch(
            soundEnabled = soundEnabled,
            onSoundEnabledChange = onSoundEnabledChange
        )
    }
}

@Composable
private fun SoundSwitch(
    soundEnabled: Boolean,
    onSoundEnabledChange: (Boolean) -> Unit,
) {
    IvyButton(
        modifier = Modifier.background(color = MaterialTheme.colorsExt.backgroundVariant),
        appearance = ButtonAppearance.Filled(ButtonStyle.Neutral),
        onClick = {
            onSoundEnabledChange(!soundEnabled)
        },
        text = {
            Text("Sounds")
            Spacer(Modifier.weight(1f))
            Switch(
                checked = soundEnabled,
                onCheckedChange = {
                    onSoundEnabledChange(it)
                },
            )
        }
    )
}

private fun LazyListScope.premiumButton(
    onPremiumClick: () -> Unit
) {
    item(key = "premium") {
        IvyButton(
            modifier = Modifier.fillMaxWidth(),
            appearance = ButtonAppearance.Filled(ButtonStyle.Primary),
            text = {
                Text("Premium")
            },
            onClick = onPremiumClick
        )
        Spacer(Modifier.height(16.dp))
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