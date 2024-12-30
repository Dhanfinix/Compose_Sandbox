package edts.android.composesandbox.screen.showcase.popup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import edts.android.composesandbox.R
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen

@Composable
fun PopupScreen(
    modifier: Modifier = Modifier
) {
    val btnModifier = Modifier

    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.popup,
        boxContent = {
            var parentPopup by remember { mutableStateOf(false) }
            var customParentPopup by remember { mutableStateOf(false) }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Button(
                        modifier = btnModifier,
                        onClick = { parentPopup = true }
                    ) {
                        Text("Parent Popup")
                    }
                }
                item {
                    Button(
                        modifier = btnModifier,
                        onClick = { customParentPopup = true }
                    ) {
                        Text("Parent Popup with Custom Position")
                    }
                }
                item {
                    var columnPopup by remember { mutableStateOf(false) }
                    Button(
                        modifier = btnModifier,
                        onClick = { columnPopup = true }
                    ) {
                        Text("On Column Popup")
                    }
                    AnimatedVisibility(columnPopup) {
                        Popup( onDismissRequest = {columnPopup = false}) {
                            Card {
                                Text(
                                    text = "This popup is rendered inside column, that's why it's appear below button",
                                )
                            }
                        }
                    }
                }
                item {
                    var paddingPopup by remember { mutableStateOf(false) }
                    Button(
                        modifier = btnModifier,
                        onClick = { paddingPopup = true }
                    ) {
                        Text("On Popup with padding")
                    }
                    AnimatedVisibility(paddingPopup) {
                        Popup(
                            onDismissRequest = {paddingPopup = false}
                        ) {
                            Card(
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = "The popup before this appear outside the screen padding, " +
                                            "it because popup is rendered in separate layer." +
                                            "To solve it we can use padding modifier to match screen padding",
                                )
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(parentPopup) {
                Popup(
                    onDismissRequest = {parentPopup = false}
                ) {
                    Card {
                        Text(
                            text = "By default popup position is on top left corner of the parent window"
                        )
                    }
                }
            }
            val positionProvider = remember { object : PopupPositionProvider{
                override fun calculatePosition(
                    anchorBounds: IntRect,
                    windowSize: IntSize,
                    layoutDirection: LayoutDirection,
                    popupContentSize: IntSize
                ): IntOffset {
                    val xOffset = (windowSize.width - popupContentSize.width)/2
                    val yOffset = (windowSize.height - popupContentSize.height)/2
                    return IntOffset(xOffset, yOffset)
                }
            } }
            AnimatedVisibility(customParentPopup) {
                Popup(
                    popupPositionProvider = positionProvider,
                    onDismissRequest = {customParentPopup = false}
                ) {
                    Card {
                        Text(
                            text = "Custom position to center of the window"
                        )
                    }
                }
            }
        }
    )
}