package edts.android.composesandbox.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import edts.android.composesandbox.R

@Composable
fun PasswordTextFieldComp(
    modifier: Modifier = Modifier,
    value: String,
    label: String = "This is password text field",
    mask: Char = '\u2022',
    onValueChange: (String) -> Unit,
) {
    var showPass by remember { mutableStateOf(false) }
    val trailingIcon =
        if (showPass) {
            R.drawable.baseline_lock_open_24
        } else {
            R.drawable.baseline_lock_outline_24
        }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        visualTransformation =
            if (showPass) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation(
                    mask = mask,
                )
            },
        value = value,
        trailingIcon = {
            Icon(
                painter = painterResource(trailingIcon),
                contentDescription = null,
                modifier =
                    Modifier.clickable {
                        showPass = !showPass
                    },
            )
        },
        onValueChange = {
            onValueChange(it)
        },
    )
}
