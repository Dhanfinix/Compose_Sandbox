package edts.android.composesandbox.component.theme_button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R

@Composable
fun ThemeButtonComp(
    modifier: Modifier = Modifier,
    mode: ThemeMode = ThemeMode.System,
    onChange: (ThemeMode) -> Unit,
) {
    val themeIcon =
        when (mode) {
            ThemeMode.Light -> R.drawable.baseline_light_mode_24
            ThemeMode.Dark -> R.drawable.baseline_dark_mode_24
            ThemeMode.System -> R.drawable.baseline_auto_mode_24
        }
    val nextTheme =
        when (mode) {
            ThemeMode.Light -> ThemeMode.Dark
            ThemeMode.Dark -> ThemeMode.System
            ThemeMode.System -> ThemeMode.Light
        }
    IconButton(
        modifier = modifier.padding(horizontal = 4.dp),
        onClick = { onChange(nextTheme) },
    ) {
        Image(
            painter = painterResource(themeIcon),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            contentDescription = "Change Theme Button",
        )
    }
}
