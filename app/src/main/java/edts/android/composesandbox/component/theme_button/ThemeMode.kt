package edts.android.composesandbox.component.theme_button

sealed class ThemeMode(
    val value: String,
) {
    data object Light : ThemeMode("LIGHT")

    data object Dark : ThemeMode("DARK")

    data object System : ThemeMode("SYSTEM")

    companion object {
        fun fromString(value: String): ThemeMode =
            when (value) {
                "LIGHT" -> Light
                "DARK" -> Dark
                else -> System
            }
    }
}
