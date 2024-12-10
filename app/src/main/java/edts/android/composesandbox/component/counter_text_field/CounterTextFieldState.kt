package edts.android.composesandbox.component.counter_text_field

data class CounterTextFieldState(
    val value: String = "",
    val maxChar: Int = 20
){
    val counter = value.length
}
