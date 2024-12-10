package edts.android.composesandbox.component.counter_text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CounterTextField(
    modifier: Modifier = Modifier,
    label: String,
    uiState: CounterTextFieldState,
    onValueChange: (String)->Unit
) {
    TextField(
        modifier = modifier,
        label = { Text(label) },
        value = uiState.value,
        onValueChange = {
            if (it.length <= uiState.maxChar) onValueChange(it)
        },
        supportingText = {
            Text(
                text = "${uiState.counter}/${uiState.maxChar}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    )
}