package edts.android.composesandbox.util

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import edts.android.composesandbox.component.theme_button.ThemeMode

object AndroidUtil {
    @Composable
    fun UpdateSystemBars(themeMode: ThemeMode) {
        val context = LocalContext.current
        val activity = context as? Activity
        val window = activity?.window ?: return
        val view = LocalView.current

        val useDarkIcons =
            themeMode == ThemeMode.Light ||
                (themeMode == ThemeMode.System && !isSystemInDarkTheme())

        val windowInsetsController = WindowCompat.getInsetsController(window, view)
        windowInsetsController.isAppearanceLightStatusBars = useDarkIcons
        windowInsetsController.isAppearanceLightNavigationBars = useDarkIcons
    }
}
