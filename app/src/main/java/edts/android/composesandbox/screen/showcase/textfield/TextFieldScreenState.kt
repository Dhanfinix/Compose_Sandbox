package edts.android.composesandbox.screen.showcase.textfield

import edts.android.composesandbox.component.counter_text_field.CounterTextFieldState
import edts.android.composesandbox.component.search.SearchState
import edts.base.android.core_resource.component.otp.OtpState

data class TextFieldScreenState(
    val normalTextField: String = "",
    val outlinedTextField: String = "",
    val basicTextField: String = "",
    val passwordTextField: String = "",
    val searchState: SearchState = SearchState(),
    val pinTextField: OtpState = OtpState(),
    val counterTextField: CounterTextFieldState = CounterTextFieldState()
)
