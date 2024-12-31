package edts.android.composesandbox.screen.showcase.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.component.PasswordTextFieldComp
import edts.android.composesandbox.component.counter_text_field.CounterTextField
import edts.android.composesandbox.component.otp.OtpComp
import edts.android.composesandbox.component.search.SearchComp
import edts.android.composesandbox.component.search.SearchDelegate
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.InterFamily
import edts.android.composesandbox.ui.theme.body1
import edts.android.composesandbox.util.LightDarkPreview

@Composable
fun TextFieldScreen(
    modifier: Modifier = Modifier,
    viewModel: TextFieldViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.text_field,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            // normal text field
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("This is normal text field")
                },
                value = uiState.normalTextField,
                onValueChange = {
                    viewModel.setNormal(it)
                },
            )
        }
        item {
            // outlined text field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("This is outlined text field")
                },
                value = uiState.outlinedTextField,
                onValueChange = {
                    viewModel.setOutlined(it)
                },
            )
        }
        item {
            // basic text field, it doesn't have any base styling,
            // best for custom text field
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.basicTextField,
                textStyle = InterFamily.body1(),
                onValueChange = {
                    viewModel.setBasic(it)
                },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier.padding(8.dp),
                    ) {
                        if (uiState.basicTextField.isEmpty()) {
                            Text("This is basic text field")
                        }
                        innerTextField()
                    }
                },
            )
        }
        item {
            // password text field
            PasswordTextFieldComp(
                label = "This is password text field",
                value = uiState.passwordTextField,
                mask = '\u2726',
            ) { viewModel.setPassword(it) }
        }
        item {
            // Search text field
            SearchComp(
                state = uiState.searchState,
                hint = "Custom text field from basic",
                delegate =
                    object : SearchDelegate {
                        override fun onChange(value: String) {
                            viewModel.setSearchQuery(value)
                        }

                        override fun onClose() {}
                    },
            )
        }
        item {
            // Pin text field
            OtpComp(
                uiState = uiState.pinTextField,
                showKeyboardInit = false,
            ) { newValue, _ ->
                viewModel.setOtpValue(newValue)
            }
        }
        item {
            // Counter text field
            CounterTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Text field with char counter",
                uiState = uiState.counterTextField,
            ) {
                viewModel.setCounter(it)
            }
        }
        item {
            // Disabled text field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "This text field is disabled",
                onValueChange = {},
                enabled = false,
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun TextFieldPreview() {
    ComposeSandboxTheme {
        TextFieldScreen(
            viewModel = TextFieldViewModel(),
        )
    }
}
