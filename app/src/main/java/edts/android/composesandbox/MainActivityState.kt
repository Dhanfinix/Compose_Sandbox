package edts.android.composesandbox

import edts.android.composesandbox.component.theme_button.ThemeMode

data class MainActivityState(
    val theme: ThemeMode = ThemeMode.System,
)
