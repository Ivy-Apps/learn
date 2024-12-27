package ui.screen.lesson.composable.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.button.PrimaryButton
import ui.screen.lesson.AnswerViewState
import ui.screen.lesson.QuestionItemViewState
import ui.screen.lesson.QuestionTypeViewState
import ui.screen.lesson.composable.ItemSpacingBig
import ui.screen.lesson.composable.item.common.QuestionCard
import ui.screen.lesson.composable.item.common.QuestionText
import ui.theme.Green
import ui.theme.IvyTheme
import ui.theme.Red

@Composable
fun QuestionLessonItem(
  viewState: QuestionItemViewState,
  modifier: Modifier = Modifier,
  onAnswerCheckChange: (QuestionTypeViewState, AnswerViewState, Boolean) -> Unit,
  onCheckClick: (List<AnswerViewState>) -> Unit
) {
  QuestionCard(
    modifier = modifier.padding(top = ItemSpacingBig),
  ) {
    QuestionText(text = viewState.question)
    if (viewState.clarification != null) {
      Spacer(Modifier.height(4.dp))
      ClarificationText(clarification = viewState.clarification)
    }
    if (viewState.type == QuestionTypeViewState.MultipleChoice) {
      Spacer(Modifier.height(4.dp))
      SelectAllThatApplyText()
    }
    viewState.answers.forEach {
      Spacer(Modifier.height(8.dp))
      AnswerItem(
        viewState = it,
        questionType = viewState.type,
        questionAnswered = viewState.answered,
        onCheckedChange = { checked ->
          onAnswerCheckChange(viewState.type, it, checked)
        }
      )
    }
    if (!viewState.answered) {
      Spacer(Modifier.height(8.dp))
      CheckButton(
        modifier = Modifier.align(Alignment.End),
        enabled = viewState.answers.any { it.selected },
        onClick = { onCheckClick(viewState.answers) }
      )
    }
  }
}

@Composable
private fun ClarificationText(
  clarification: String,
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = clarification,
    color = MaterialTheme.colors.secondary,
    style = IvyTheme.typography.caption,
    fontWeight = FontWeight.Medium
  )
}

@Composable
private fun SelectAllThatApplyText(
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = "Select all that apply:",
    color = Color.Gray,
    style = IvyTheme.typography.caption,
    fontWeight = FontWeight.SemiBold,
  )
}

@Composable
private fun AnswerItem(
  viewState: AnswerViewState,
  questionType: QuestionTypeViewState,
  questionAnswered: Boolean,
  modifier: Modifier = Modifier,
  onCheckedChange: (Boolean) -> Unit
) {
  val correct = viewState.correct.takeIf { questionAnswered }
  Row(
    modifier = modifier.clickable(
      enabled = correct == null,
      onClick = { onCheckedChange(!viewState.selected) }
    ),
    verticalAlignment = Alignment.CenterVertically
  ) {
    AnswerCheckbox(
      questionType = questionType,
      selected = viewState.selected,
      correct = correct,
      onCheckedChange = onCheckedChange
    )
    Spacer(modifier = Modifier.width(12.dp))
    Column {
      AnswerText(text = viewState.text)
      if (viewState.explanation != null && questionAnswered) {
        Spacer(modifier = Modifier.height(4.dp))
        AnswerExplanationText(
          text = viewState.explanation,
          correct = viewState.correct
        )
      }
    }
  }
}

@Composable
private fun AnswerCheckbox(
  questionType: QuestionTypeViewState,
  selected: Boolean,
  correct: Boolean?,
  modifier: Modifier = Modifier,
  onCheckedChange: (Boolean) -> Unit
) {
  val answeredColor = when (correct) {
    true -> Green
    false -> Red
    null -> MaterialTheme.colors.surface
  }
  Checkbox(
    modifier = modifier,
    checked = selected,
    onCheckedChange = onCheckedChange,
    enabled = correct == null,
    colors = CheckboxDefaults.colors(
      checkedColor = MaterialTheme.colors.primary,
      checkmarkColor = MaterialTheme.colors.onPrimary,
      disabledColor = answeredColor,
    )
  )
}

@Composable
private fun AnswerText(
  text: String,
  modifier: Modifier = Modifier
) {
  Text(
    modifier = modifier,
    text = text,
    style = IvyTheme.typography.b2
  )
}

@Composable
private fun AnswerExplanationText(
  text: String,
  correct: Boolean,
  modifier: Modifier = Modifier
) {
  Text(
    modifier = modifier,
    text = text,
    color = if (correct) {
      Green
    } else {
      Red
    },
    style = IvyTheme.typography.caption
  )
}

@Composable
private fun CheckButton(
  modifier: Modifier = Modifier,
  enabled: Boolean,
  onClick: () -> Unit
) {
  PrimaryButton(
    modifier = modifier,
    enabled = enabled,
    text = "CHECK",
    onClick = onClick
  )
}