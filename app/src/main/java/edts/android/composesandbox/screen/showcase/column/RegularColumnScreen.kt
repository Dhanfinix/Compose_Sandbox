package edts.android.composesandbox.screen.showcase.column

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edts.android.composesandbox.R
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen

@Composable
fun RegularColumnScreen(
    modifier: Modifier = Modifier,
    onBack: ()->Unit
) {
    val dummyData = listOf(
        ColumnData(ColumnType.TEXT, "ColumnType.Text will create a text component"),
        ColumnData(ColumnType.IMAGE, "https://picsum.photos/id/237/720/480"),
        ColumnData(ColumnType.TEXT, "ColumnType.IMAGE will create a image component like above and below"),
        ColumnData(ColumnType.IMAGE, "https://picsum.photos/id/100/720/480"),
        ColumnData(ColumnType.BUTTON, "While, ColumnType.BUTTON will create a button component")
    )
    val context = LocalContext.current
    ShowcaseBaseScreen(
        title = R.string.regular_column,
        modifier = modifier,
        onBack = onBack
    ){
        item {
            Text("This app and it's components already use a lot of column and lazy column, " +
                    "this page will only focus on LazyColumn as replacement of RecyclerView")
        }
        item {
            Text("Text below only created with 3 lines.")
        }
        for (i in 1..10){
            item{ Text("Item $i of 10") }
        }
        item {
            HorizontalDivider()
        }
        item {
            Text("To make item based on it's type, just use when method")
        }
        items(dummyData){data->
            when(data.type){
                ColumnType.TEXT -> {
                    Text(data.value)
                }
                ColumnType.IMAGE -> {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(data.value)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.image_placeholder),
                        contentDescription = null
                    )
                }
                else -> {
                    Button(
                        onClick = { Toast.makeText(context, data.value, Toast.LENGTH_SHORT).show()}
                    ) {
                        Text("Click to see it's value")
                    }
                }
            }
        }
        item {
            HorizontalDivider()
        }
        item {
            Text(
                "Above list doesn't need any holder or adapter like in RecyclerView",
                Modifier.padding(bottom = 16.dp)
            )
        }
    }
}