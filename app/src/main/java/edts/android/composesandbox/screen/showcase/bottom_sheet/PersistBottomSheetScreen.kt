package edts.android.composesandbox.screen.showcase.bottom_sheet

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.screen.showcase.text.TextScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersistBottomSheetScreen(modifier: Modifier = Modifier) {
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.persist_bottom_sheet,
        boxContent = {
            BottomSheetScaffold(
                sheetContent = { TextScreen() },
                sheetPeekHeight = 150.dp,
            ) {
                Text(
                    "This bottom sheet have a long content and " +
                        "cant be dismissed fully.\n" +
                        "Just for demo, below is TextScreen component, " +
                        "Swipe to see..",
                )
            }
        },
    )
}
