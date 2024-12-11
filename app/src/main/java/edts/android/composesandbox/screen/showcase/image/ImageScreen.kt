package edts.android.composesandbox.screen.showcase.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import edts.android.composesandbox.R
import edts.android.composesandbox.component.shape.RoundedPolygonShape
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.subtitle2
import edts.android.composesandbox.util.LightDarkPreview

@Composable
fun ImageScreen(
    modifier: Modifier = Modifier,
    onBack: ()->Unit
) {
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.image,
        onBack = onBack
    ) {
        item {
            Text(
                text = "Local Image\n" +
                        "Touch to apply an effect",
                style = MontserratFamily.subtitle2()
            )
        }
        item {
            var counter by remember { mutableIntStateOf(0) }
            val scaleMode by remember(counter){
                derivedStateOf {
                    when(counter){
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
                    when(scaleMode){
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .clickable {
                            if (counter <= 5) counter++
                            else counter = 0
                        }
                )
                Text(
                    text = scaleName,
                    color = Color.Yellow,
                    textAlign = TextAlign.End,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
        item {
            var counter by remember { mutableIntStateOf(0) }

            val shape by remember(counter) {
                derivedStateOf {
                    when(counter){
                        0 -> RectangleShape
                        1 -> RoundedCornerShape(20.dp)
                        2 -> CircleShape
                        3 -> CutCornerShape(50)
                        4 -> RoundedPolygonShape(
                            RoundedPolygon(
                                numVertices = 6,
                                rounding = CornerRounding(0.2f)
                            )
                        )
                        else -> RectangleShape
                    }
                }
            }
            Image(
                painter = painterResource(R.drawable.montains),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        shadowElevation = 10.dp.toPx()
                        this.shape = shape
                        clip = true
                    }
                    .clickable {
                        if (counter <= 3) counter++
                        else counter = 0
                    }
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun ImageScreenPreview() {
    ComposeSandboxTheme {
        ImageScreen {}
    }
}