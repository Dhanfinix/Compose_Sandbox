package edts.android.composesandbox.component.segment_button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.fastForEachIndexed
import edts.android.composesandbox.R
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.util.LightDarkPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonComp(
    modifier: Modifier = Modifier,
    uiState: SegmentedState,
    onChecked: (Int) -> Unit,
) {
    if (uiState.isMulti) {
        MultiChoiceSegmentedButtonRow(modifier) {
            uiState.options.fastForEachIndexed { index, segmentedData ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index, uiState.options.size),
                    icon = {
                        SegmentedButtonDefaults.Icon(segmentedData.checked) {
                            Icon(
                                painter = painterResource(segmentedData.icon),
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                            )
                        }
                    },
                    onCheckedChange = { onChecked(index) },
                    checked = segmentedData.checked,
                ) {
                    Text(segmentedData.option)
                }
            }
        }
    } else {
        SingleChoiceSegmentedButtonRow {
            uiState.options.fastForEachIndexed { index, segmentedData ->
                val selected = uiState.selectedIndex == index
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index, uiState.options.size),
                    icon = {
                        SegmentedButtonDefaults.Icon(selected) {
                            Icon(
                                painter = painterResource(segmentedData.icon),
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                            )
                        }
                    },
                    selected = selected,
                    onClick = { onChecked(index) },
                ) {
                    Text(segmentedData.option)
                }
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun SegmentedButtonPreview() {
    ComposeSandboxTheme {
        SegmentedButtonComp(
            uiState =
                SegmentedState(
                    options =
                        listOf(
                            SegmentedData("Add", R.drawable.baseline_add_24),
                            SegmentedData("Search", R.drawable.baseline_search_24, true),
                            SegmentedData("Lock", R.drawable.baseline_lock_outline_24),
                        ),
                ),
        ) { }
    }
}
