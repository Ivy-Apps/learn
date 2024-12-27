package ui.screen.settings.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import component.BackButton
import component.LearnScaffold
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
import component.button.IvySwitch
import component.platformHorizontalPadding
import ui.screen.settings.SettingsViewEvent
import ui.screen.settings.SettingsViewState
import ui.theme.Gray
import ui.theme.IvyTheme

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
                contentPadding = PaddingValues(horizontal = horizontalPadding)
            ) {
                sectionDivider(text = "Premium")
                premiumButton(
                    onPremiumClick = {
                        onEvent(SettingsViewEvent.OnPremiumClick)
                    }
                )
                sectionDivider("App")
                appSettingsSection(
                    soundEnabled = viewState.soundEnabled,
                    onSoundEnabledChange = {
                        onEvent(SettingsViewEvent.OnSoundEnabledChange(it))
                    }
                )
                sectionDivider("Account")
                privacyButton(
                    onPrivacyClick = {
                        onEvent(SettingsViewEvent.OnPrivacyClick)
                    }
                )
                spacerItem(
                    key = "spacer 1",
                    height = 8.dp
                )
                logoutButton(
                    onLogOutClick = {
                        onEvent(SettingsViewEvent.OnLogoutClick)
                    }
                )
                spacerItem(
                    key = "spacer 2",
                    height = 8.dp
                )
                deleteAccountButton(
                    onDeleteAccountClick = {
                        onEvent(SettingsViewEvent.OnDeleteAccountClick)
                    }
                )
                spacerItem(
                    key = "spacer 3",
                    height = 8.dp
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

    if (viewState.deleteDialog != null) {
        DeleteAccountConfirmationDialog(
            viewState = viewState.deleteDialog,
            onConfirmDeleteAccountClick = {
                onEvent(SettingsViewEvent.OnConfirmDeleteAccountClick)
            },
            onCancelDeleteAccountClick = {
                onEvent(SettingsViewEvent.OnCancelDeleteAccountClick)
            }
        )
    }
}

private fun LazyListScope.sectionDivider(text: String) {
    item(key = text) {
        Text(
            modifier = Modifier.padding(
                start = 24.dp,
                top = 24.dp,
                bottom = 8.dp
            ),
            text = text,
            style = IvyTheme.typography.b1,
            fontWeight = FontWeight.SemiBold,
            color = Gray
        )
    }
}

private fun LazyListScope.premiumButton(
    onPremiumClick: () -> Unit
) {
    item(key = "premium") {
        IvyButton(
            modifier = Modifier.fillMaxWidth(),
            appearance = ButtonAppearance.Filled(ButtonStyle.Primary),
            text = {
                Text("Upgrade to Premium")
            },
            onClick = onPremiumClick
        )
    }
}

private fun LazyListScope.appSettingsSection(
    soundEnabled: Boolean,
    onSoundEnabledChange: (Boolean) -> Unit,
) {
    item(key = "app") {
        SoundSwitch(
            soundEnabled = soundEnabled,
            onSoundEnabledChange = onSoundEnabledChange
        )
    }
}

@Composable
private fun SoundSwitch(
    soundEnabled: Boolean,
    modifier: Modifier = Modifier,
    onSoundEnabledChange: (Boolean) -> Unit,
) {
    IvySwitch(
        modifier = modifier,
        checked = soundEnabled,
        onCheckedChange = {
            onSoundEnabledChange(!soundEnabled)
        },
        text = {
            Text(text = "Sounds")
        }
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

private fun LazyListScope.logoutButton(
    onLogOutClick: () -> Unit
) {
    item(key = "log-out") {
        IvyButton(
            modifier = Modifier.fillMaxWidth(),
            appearance = ButtonAppearance.Outlined(ButtonStyle.Neutral),
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null
                )
            },
            text = {
                Text("Log out")
            },
            onClick = onLogOutClick
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
                    Text("Terms of Service")
                },
                onClick = onTermsOfUseClick,
            )
            IvyButton(
                appearance = ButtonAppearance.Text(style = ButtonStyle.Neutral),
                text = {
                    Text("Privacy Policy")
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