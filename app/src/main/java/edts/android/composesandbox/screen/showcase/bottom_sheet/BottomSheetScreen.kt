package edts.android.composesandbox.screen.showcase.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.navigation.Destination
import edts.android.composesandbox.navigation.LocalNavController
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(modifier: Modifier = Modifier) {
    var showModal by remember { mutableStateOf(false) }
    var showModal2 by remember { mutableStateOf(false) }
    val navController = LocalNavController.current
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.bottom_sheet,
        boxContent = {
            Column {
                Button(
                    onClick = { showModal = true },
                ) {
                    Text("Modal Bottom Sheet")
                }
                Button(
                    onClick = { navController.navigate(Destination.PersistentBottomSheet()) },
                ) {
                    Text("Persistent Bottom Sheet")
                }
            }

            AnimatedVisibility(showModal2) {
                ModalBottomSheet(
                    onDismissRequest = {showModal2 = false},
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "This is also modal bottom sheet, " +
                                "but created on top of previous one.\n" +
                                "As you can see on code, even though the line is " +
                                "added before first bottom sheet, it still appear " +
                                "on top."
                    )
                }
            }

            AnimatedVisibility(showModal) {
                ModalBottomSheet(
                    onDismissRequest = {showModal = false},
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Text(
                            text = "This is modal bottom sheet, it's temporary " +
                                    "bottom sheet with dim background to focus user",
                        )
                        Button(
                            onClick = { showModal2 = true },
                        ) {
                            Text(
                                text = "Another Modal Bottom Sheet",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    )
}