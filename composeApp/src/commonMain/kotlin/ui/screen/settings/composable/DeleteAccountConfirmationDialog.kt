package ui.screen.settings.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.button.ButtonAppearance
import component.button.ButtonStyle
import component.button.IvyButton
import ui.theme.colorsExt

@Composable
fun DeleteAccountConfirmationDialog(
    modifier: Modifier = Modifier,
    onConfirmDeleteAccountClick: () -> Unit,
    onCancelDeleteAccountClick: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        title = {
            Text(
                text = "Confirm Account Deletion",
                style = MaterialTheme.typography.subtitle1
            )
        },
        text = {
            Text(
                text = "By proceeding, you confirm your request to permanently delete your account and all " +
                        "associated data. This action is irreversible and cannot be undone. Please review our " +
                        "Privacy Policy and Terms of Service for further details.",
                style = MaterialTheme.typography.subtitle2
            )
        },
        onDismissRequest = onCancelDeleteAccountClick,
        confirmButton = {
            IvyButton(
                appearance = ButtonAppearance.Filled(ButtonStyle.Destructive),
                onClick = onConfirmDeleteAccountClick
            )
        },
        dismissButton = {
            IvyButton(
                appearance = ButtonAppearance.Filled(ButtonStyle.Neutral),
                onClick = onCancelDeleteAccountClick
            )
        },
        backgroundColor = MaterialTheme.colorsExt.backgroundVariant,
        contentColor = MaterialTheme.colorsExt.onBackgroundVariant
    )
}