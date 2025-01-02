package edts.android.composesandbox.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import edts.android.composesandbox.component.theme_button.ThemeMode

val LocalNavController =
    compositionLocalOf<NavHostController> {
        error("No LocalNavController provided")
    }

val LocalTheme =
    compositionLocalOf<ThemeMode> { ThemeMode.System }
