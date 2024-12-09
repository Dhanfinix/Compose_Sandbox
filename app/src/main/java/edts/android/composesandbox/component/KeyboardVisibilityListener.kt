package edts.android.composesandbox.component

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

/**
 * AI Generated by Microsoft Copilot
 */

@Composable
fun KeyboardVisibilityListener(
    onKeyboardVisibilityChanged: (Boolean) -> Unit
) {
    val view = LocalView.current
    val density = LocalDensity.current
    var isKeyboardVisible by remember { mutableStateOf(false) }

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            // Convert pixels to dp
            val keypadHeightDp = with(density) { keypadHeight.toDp() }

            val isVisible = keypadHeightDp > 100.dp // Arbitrary threshold for keyboard visibility

            if (isVisible != isKeyboardVisible) {
                isKeyboardVisible = isVisible
                onKeyboardVisibilityChanged(isVisible)
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}