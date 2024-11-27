package ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navigation.Route

@Composable
fun NotFoundPage(
    route: Route,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Page not found :/",
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.error
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "No routing found for: $route",
            style = MaterialTheme.typography.body1
        )
    }
}