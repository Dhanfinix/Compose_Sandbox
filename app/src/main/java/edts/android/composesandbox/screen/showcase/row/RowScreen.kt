package edts.android.composesandbox.screen.showcase.row

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edts.android.composesandbox.R
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.InterFamily
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.body1
import edts.android.composesandbox.ui.theme.body2
import edts.android.composesandbox.ui.theme.button
import edts.android.composesandbox.ui.theme.caption

@Composable
fun RowScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.row
    ){
        item {
            LazyRow {
                item {
                    Text(
                        "[Scroll horizontally]",
                        style = MontserratFamily.body1()
                    )
                }
                item {
                    Text(
                        "Not much to say here, ",
                        style = InterFamily.body2()
                    )
                }
                item {
                    Text(
                        "similar with column, ",
                        style = MontserratFamily.caption()
                    )
                }
                item {
                    Text(
                        "just in horizontal arrangement",
                        style = InterFamily.button())
                }
            }
        }
        item {
            LazyRow {
                val listImage = listOf(
                    "https://picsum.photos/id/237/720/480",
                    "https://picsum.photos/id/238/720/480",
                    "https://picsum.photos/id/239/720/480",
                    "https://picsum.photos/id/240/720/480",
                    "https://picsum.photos/id/241/720/480"
                )
                listImage.forEach {
                    item {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(it)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.image_placeholder),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}