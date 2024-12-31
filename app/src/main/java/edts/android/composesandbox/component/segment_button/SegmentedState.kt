package edts.android.composesandbox.component.segment_button

data class SegmentedState(
    val isMulti: Boolean = true,
    val options: List<SegmentedData>,
    /** selectedIndex is for Single Choice */
    val selectedIndex: Int = -1,
)
