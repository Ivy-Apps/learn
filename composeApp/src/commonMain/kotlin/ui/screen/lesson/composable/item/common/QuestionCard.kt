package ui.screen.lesson.composable.item.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.ScreenType.*
import component.screenType

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        val screenType = screenType()
        Column(
            modifier = Modifier.padding(
                horizontal = when (screenType) {
                    Mobile -> 16.dp
                    Tablet -> 24.dp
                    Desktop -> 24.dp
                },
                vertical = when (screenType) {
                    Mobile -> 16.dp
                    Tablet -> 16.dp
                    Desktop -> 24.dp
                },
            ),
            content = content,
        )
    }
}