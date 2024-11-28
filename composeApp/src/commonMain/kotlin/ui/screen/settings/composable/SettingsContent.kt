package ui.screen.settings.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
import component.platformHorizontalPadding
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            val horizontalPadding = platformHorizontalPadding()
            LazyColumn(
                modifier = Modifier.widthIn(max = 500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(horizontal = horizontalPadding)
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
                privacyButton(
                    onPrivacyClick = {
                        onEvent(SettingsViewEvent.OnPrivacyClick)
                    }
                )
                deleteAccountButton(
                    onDeleteAccountClick = {
                        onEvent(SettingsViewEvent.OnDeleteAccountClick)
                    }
                )
                legalFooter(
                    onTermsOfUseClick = {
                        onEvent(SettingsViewEvent.OnTermsOfUseClick)
                    },
                    onPrivacyPolicyClick = {
                        onEvent(SettingsViewEvent.OnPrivacyPolicyClick)
                    }
                )
            }
        }
    }
}

private fun LazyListScope.premiumButton(
    onPremiumClick: () -> Unit
) {
    item(key = "premium") {
        Column {
            Title("App")
            IvyButton(
                modifier = Modifier.fillMaxWidth(),
                appearance = ButtonAppearance.Filled(ButtonStyle.Primary),
                text = {
                    Text("Premium")
                },
                onClick = onPremiumClick
            )
        }
    }
}

private fun LazyListScope.appSettingsSection(
    soundEnabled: Boolean,
    onSoundEnabledChange: (Boolean) -> Unit,
) {
    item(key = "app") {
        Column {
            Title("App")
            Spacer(Modifier.height(12.dp))
            SoundSwitch(
                soundEnabled = soundEnabled,
                onSoundEnabledChange = onSoundEnabledChange
            )
        }
    }
}

@Composable
private fun SoundSwitch(
    soundEnabled: Boolean,
    modifier: Modifier = Modifier,
    onSoundEnabledChange: (Boolean) -> Unit,
) {
    IvyButton(
        modifier = modifier,
        appearance = ButtonAppearance.Filled(ButtonStyle.Neutral),
        text = {
            Text("Sounds")
            Spacer(Modifier.weight(1f))
            Switch(
                modifier = Modifier.defaultMinSize(minHeight = 0.dp),
                checked = soundEnabled,
                onCheckedChange = {
                    onSoundEnabledChange(it)
                },
            )
        },
        onClick = {
            onSoundEnabledChange(!soundEnabled)
        },
    )
}

private fun LazyListScope.privacyButton(
    onPrivacyClick: () -> Unit
) {
    item(key = "privacy") {
        IvyButton(
            modifier = Modifier.fillMaxWidth(),
            appearance = ButtonAppearance.Outlined(ButtonStyle.Neutral),
            text = {
                Text("Privacy")
            },
            onClick = onPrivacyClick
        )
    }
}

private fun LazyListScope.deleteAccountButton(
    onDeleteAccountClick: () -> Unit
) {
    item(key = "delete-account") {
        IvyButton(
            modifier = Modifier.fillMaxWidth(),
            appearance = ButtonAppearance.Outlined(style = ButtonStyle.Destructive),
            text = {
                Text("Delete account")
            },
            onClick = onDeleteAccountClick
        )
    }
}

private fun LazyListScope.legalFooter(
    onTermsOfUseClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit
) {
    item(key = "legal-footer") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IvyButton(
                appearance = ButtonAppearance.Text(style = ButtonStyle.Neutral),
                text = {
                    Text("Terms of use")
                },
                onClick = onTermsOfUseClick,
            )
            IvyButton(
                appearance = ButtonAppearance.Text(style = ButtonStyle.Neutral),
                text = {
                    Text("Privacy policy")
                },
                onClick = onPrivacyPolicyClick,
            )
        }
    }
}

private fun LazyListScope.spacerItem(key: String, height: Dp) {
    item(key) {
        Spacer(Modifier.height(height))
    }
}