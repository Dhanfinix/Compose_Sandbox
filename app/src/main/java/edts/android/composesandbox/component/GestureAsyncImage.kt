package edts.android.composesandbox.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edts.android.composesandbox.R
import edts.android.composesandbox.util.conditional

/** This Code is co-created with Claude AI
 * @see <a href=https://claude.site/artifacts/a6857816-b591-4b25-8097-d01f22741d80>Claude AI</a>
 * */

@Composable
fun GestureAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit
) {
    val myUri by remember(model) { mutableStateOf(model) }
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var panSpeed by remember { mutableFloatStateOf(1.5f) }

    val maxScale = 3f
    val minScale = 1f
    LaunchedEffect(scale) {
        panSpeed = if (scale < 2) 1.5f else 4.5f
    }

    Box(modifier
        .background(
            MaterialTheme.colorScheme.surfaceContainer,
            shape = RoundedCornerShape(20.dp)
        )
        .clip(RoundedCornerShape(20.dp))
    ){
        AsyncImage(
            model = myUri,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { }
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(minScale, maxScale)
                        offsetX += pan.x * panSpeed
                        offsetY += pan.y * panSpeed
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { offset ->
                            if (scale > minScale) {
                                // Reset to original size
                                scale = minScale
                                offsetX = 0f
                                offsetY = 0f
                            } else {
                                // Calculate the touch point relative to the center
                                val centerX = size.width / 2f
                                val centerY = size.height / 2f

                                // Calculate the touch offset from center
                                val touchOffsetX = offset.x - centerX
                                val touchOffsetY = offset.y - centerY

                                // Calculate new offset to zoom into touch point
                                offsetX = -touchOffsetX * (maxScale - 1)
                                offsetY = -touchOffsetY * (maxScale - 1)

                                // Ensure offset stays within bounds
                                val maxX = (size.width * (maxScale - 1)) / 2
                                val maxY = (size.height * (maxScale - 1)) / 2

                                offsetX = offsetX.coerceIn(-maxX, maxX)
                                offsetY = offsetY.coerceIn(-maxY, maxY)

                                scale = maxScale
                            }
                        }
                    )
                },
            contentScale = contentScale
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val color = MaterialTheme.colorScheme.primary
            val onColor = MaterialTheme.colorScheme.onPrimary
            fun btnModifier(isLeft: Boolean) = Modifier
                .conditional(isLeft,
                    ifTrue = {background(color, RoundedCornerShape(topEnd = 30.dp))},
                    ifFalse = {background(color, RoundedCornerShape(topStart = 30.dp))}
                )
                .padding(16.dp)
//            Image(
//                painter = painterResource(id = R.drawable.baseline_find_replace_24),
//                contentDescription = null,
//                modifier = btnModifier(true),
//                colorFilter = ColorFilter.tint(onColor)
//            )
            Spacer(Modifier)
            AnimatedVisibility(
                visible = scale != 1f || offsetY != 0f || offsetX != 0f,
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_crop_free_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(onColor),
                    modifier = btnModifier(false)
                        .clickable {
                            scale = 1f
                            offsetX = 0f
                            offsetY = 0f
                        }
                )
            }
        }
    }
}
