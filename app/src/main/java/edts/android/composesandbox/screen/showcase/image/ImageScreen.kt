package edts.android.composesandbox.screen.showcase.image

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edts.android.composesandbox.R
import edts.android.composesandbox.component.GestureAsyncImage
import edts.android.composesandbox.component.shape.MorphPolygonShape
import edts.android.composesandbox.component.shape.RoundedPolygonShape
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.subtitle2
import edts.android.composesandbox.util.LightDarkPreview

@Composable
fun ImageScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.image_shape,
    ) {
        item {
            Text(
                text =
                    "Local Image\n" +
                        "Touch to apply an effect",
                style = MontserratFamily.subtitle2(),
            )
        }
        item {
            var counter by remember { mutableIntStateOf(0) }
            val scaleMode by remember(counter) {
                derivedStateOf {
                    when (counter) {
                        0 -> ContentScale.FillBounds
                        1 -> ContentScale.Inside
                        2 -> ContentScale.FillWidth
                        3 -> ContentScale.FillHeight
                        4 -> ContentScale.Crop
                        5 -> ContentScale.Fit
                        else -> ContentScale.None
                    }
                }
            }
            val scaleName by remember(scaleMode) {
                derivedStateOf {
                    when (scaleMode) {
                        ContentScale.FillBounds -> "FillBounds"
                        ContentScale.Inside -> "Inside"
                        ContentScale.FillWidth -> "FillWidth"
                        ContentScale.FillHeight -> "FillHeight"
                        ContentScale.Crop -> "Crop"
                        ContentScale.Fit -> "Fit"
                        else -> "None"
                    }
                }
            }

            Box {
                Image(
                    painter = painterResource(R.drawable.montains),
                    contentDescription = null,
                    contentScale = scaleMode,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Gray)
                            .clickable {
                                if (counter <= 5) {
                                    counter++
                                } else {
                                    counter = 0
                                }
                            },
                )
                Text(
                    text = scaleName,
                    color = Color.Yellow,
                    textAlign = TextAlign.End,
                    modifier = Modifier.align(Alignment.BottomEnd),
                )
            }
        }
        item {
            var counter by remember { mutableIntStateOf(0) }

            val shape by remember(counter) {
                derivedStateOf {
                    when (counter) {
                        0 -> RectangleShape
                        1 -> RoundedCornerShape(20.dp)
                        2 -> CircleShape
                        3 -> CutCornerShape(50)
                        4 ->
                            RoundedPolygonShape(
                                RoundedPolygon(
                                    numVertices = 6,
                                    rounding = CornerRounding(0.2f),
                                ),
                            )
                        5 ->
                            RoundedPolygonShape(
                                RoundedPolygon.star(
                                    5,
                                    rounding = CornerRounding(20f),
                                ),
                            )
                        else -> RectangleShape
                    }
                }
            }
            Image(
                painter = painterResource(R.drawable.montains),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(200.dp)
                        .graphicsLayer {
                            shadowElevation = 10.dp.toPx()
                            this.shape = shape
                            clip = true
                        }.clickable {
                            if (counter <= 4) {
                                counter++
                            } else {
                                counter = 0
                            }
                        },
            )
        }
        item {
            val shapeA =
                remember {
                    RoundedPolygon(
                        6,
                        rounding = CornerRounding(0.2f),
                    )
                }
            val shapeB =
                remember {
                    RoundedPolygon.star(
                        6,
                        rounding = CornerRounding(0.1f),
                    )
                }
            val morph =
                remember {
                    Morph(shapeA, shapeB)
                }
            val interactionSource =
                remember {
                    MutableInteractionSource()
                }
            val isPressed by interactionSource.collectIsPressedAsState()
            val animatedProgress =
                animateFloatAsState(
                    targetValue = if (isPressed) 1f else 0f,
                    label = "progress",
                    animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
                )
            Box {
                Image(
                    painter = painterResource(R.drawable.montains),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(200.dp)
                            .graphicsLayer {
                                shadowElevation = 10.dp.toPx()
                                shape = MorphPolygonShape(morph, animatedProgress.value)
                                clip = true
                            }.clickable(
                                interactionSource = interactionSource,
                                indication = null,
                            ) {},
                )
                Text(
                    text = "Press and Hold",
                    color = Color.Yellow,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
        item {
            Text(
                text = "Use common gesture",
                style = MontserratFamily.subtitle2(),
                modifier = Modifier.padding(top = 16.dp),
            )
        }
        item {
            GestureAsyncImage(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                model = "https://lipsum.app/random/720x480",
                contentScale = ContentScale.FillWidth,
            )
        }
        item {
            Text(
                text = "Load Image from URL",
                style = MontserratFamily.subtitle2(),
                modifier = Modifier.padding(top = 16.dp),
            )
        }
        item {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(context)
                        .data("https://lipsum.app/random/720x480")
                        .crossfade(true)
                        .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.image_placeholder),
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun ImageScreenPreview() {
    ComposeSandboxTheme {
        ImageScreen()
    }
}
