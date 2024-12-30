package edts.android.composesandbox.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.InterFamily
import edts.android.composesandbox.ui.theme.headline3

@Composable
fun ChangeNameDialogComp(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onSave: (String)->Unit,
    onDismiss: ()->Unit
) {
    var username by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    val doSave = {
        onSave(username)
        onDismiss()
    }

    AnimatedVisibility(isVisible) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Card{
                Column(
                    modifier = modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Change Username",
                        style = InterFamily.headline3(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .padding(top = 16.dp, bottom = 32.dp),
                        value = username,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { doSave() }
                        ),
                        singleLine = true,
                        placeholder = {
                            Text("Write your username here...")
                        },
                        onValueChange = { username = it }
                    )

                    Row(
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        TextButton(
                            onClick = { onDismiss() }
                        ) {
                            Text("Dismiss")
                        }
                        TextButton(
                            onClick = { doSave() }
                        ) {
                            Text("Confirm")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DialogPreview() {
    ComposeSandboxTheme {
        ChangeNameDialogComp(
            isVisible = true,
            onSave = {},
            onDismiss = {}
        )
    }
}