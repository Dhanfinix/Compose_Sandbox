package edts.android.composesandbox.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import edts.android.composesandbox.ui.theme.ColorNeutral20
import edts.android.composesandbox.ui.theme.ColorNeutral30

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else if (ifFalse != null) {
        then(ifFalse(Modifier))
    } else {
        this
    }
}

/**
 * Extension function on Modifier to apply a shimmer effect, typically used for loading states.
 *
 * The shimmer effect is created by drawing a linear gradient that moves across the content,
 * simulating a loading placeholder. The animation runs infinitely until the state changes.
 *
 * @return A Modifier that applies the shimmer effect to the composable it is attached to.
 */
fun Modifier.shimmer(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1500)
        ), label = "shimmer"
    )

    this
        .onGloballyPositioned { size = it.size }
        .drawWithCache {
            val brush = Brush.linearGradient(
                colors = listOf(
                    ColorNeutral20,
                    ColorNeutral30,
                    ColorNeutral20,
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            )
            onDrawWithContent {
                drawContent()
                drawRect(brush)
            }
        }
}