package edts.android.composesandbox.component.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.InterFamily
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.body1
import edts.android.composesandbox.ui.theme.caption
import edts.android.composesandbox.ui.theme.headline1
import edts.android.composesandbox.ui.theme.subtitle1
import edts.base.android.core_resource.component.otp.OtpState

/**
 * This Component is taken from this repo
 * https://github.com/banmarkovic/OtpTextField/blob/master/app/src/main/java/com/ban/otptextfield/OtpTextField.kt
 */
@Composable
fun OtpComp(
    modifier: Modifier = Modifier,
    uiState: OtpState,
    otpCount: Int = 4,
    cursorIndicator: Boolean = true,
    showKeyboardInit: Boolean = true,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        if (showKeyboardInit){
            focusRequester.requestFocus()
        }
        if (uiState.otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    var otpText by remember { mutableStateOf(uiState.otpText) }

    // Compare before invoking the callback to prevent multiple callback
    LaunchedEffect(otpText) {
        if (otpText != uiState.otpText) {
            onOtpTextChange.invoke(otpText, otpText.length == otpCount)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            modifier = modifier.focusRequester(focusRequester),
            value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
            onValueChange = {
                if (it.text.length <= otpCount) {
                    otpText = it.text
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(otpCount) { index ->
                        CharView(
                            index = index,
                            text = otpText,
                            type = uiState.otpType,
                            cursorIndicator = cursorIndicator
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        )

       if (uiState.otpType == OtpType.ERROR){
           uiState.alertMsg?.let {
              Text(
                  modifier = Modifier.padding(top = 12.dp),
                  text = it,
                  style = MontserratFamily.caption(),
                  color = uiState.otpType.contentColor
              )
           }
       }
    }


}

@Composable
private fun CharView(
    index: Int,
    text: String,
    type: OtpType,
    cursorIndicator: Boolean
) {
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    val isActive = (index == text.length) && cursorIndicator
    Text(
        modifier = Modifier
            .width(40.dp)
            .background(
                color = type.containerColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = if (isActive) 3.dp else 1.dp,
                color = if(isActive) type.activeBorderColor else type.borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        text = char,
        style = InterFamily.headline1(),
        color = type.contentColor,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOtpComp(
    @PreviewParameter(PreviewProviderOtp::class) state: OtpState
) {
    ComposeSandboxTheme {
        Surface(
            Modifier.padding(16.dp)
        ) {
            OtpComp(uiState = state) {_, _-> }
        }
    }
}

private class PreviewProviderOtp : PreviewParameterProvider<OtpState> {
    override val values = sequenceOf(
        OtpState(),
        OtpState("12"),
        OtpState("1234"),
        OtpState(
            "3214",
            OtpType.ERROR,
            "Kode yang Anda masukan salah, mohon cek kembali."
        )
    )
}