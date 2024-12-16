package edts.android.composesandbox.screen.showcase.column

import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import edts.android.composesandbox.R
import edts.android.composesandbox.component.mainItem.MainItemState
import edts.android.composesandbox.navigation.Destination
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen

@Composable
fun ColumnScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onBack: ()->Unit
) {
    val columnBtn = listOf(
        MainItemState(R.string.regular_column, Destination.RegularColumn())
    )
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.column,
        onBack = onBack
    ){
        items(columnBtn){
            Button(
                onClick = { navController.navigate(it.route) }
            ) {
                Text(stringResource(it.title))
            }
        }
    }
}