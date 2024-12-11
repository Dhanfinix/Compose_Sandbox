package edts.android.composesandbox.screen.showcase.button

import edts.android.composesandbox.R
import edts.android.composesandbox.component.segment_button.SegmentedData
import edts.android.composesandbox.component.segment_button.SegmentedState

data class ButtonScreenState(
    val multiSegmentedState: SegmentedState = SegmentedState(
        options = listOf(
            SegmentedData("Multi", R.drawable.baseline_add_24),
            SegmentedData("Segmented", R.drawable.baseline_search_24),
            SegmentedData("Button", R.drawable.baseline_lock_outline_24),
        )
    ),
    val singleSegmentedState: SegmentedState = SegmentedState(
        isMulti = false,
        options = listOf(
            SegmentedData("Single", R.drawable.baseline_add_24),
            SegmentedData("Segmented", R.drawable.baseline_search_24),
            SegmentedData("Button", R.drawable.baseline_lock_outline_24),
        )
    )
)
