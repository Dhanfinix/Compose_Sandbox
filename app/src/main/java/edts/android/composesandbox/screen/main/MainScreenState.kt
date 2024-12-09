package edts.android.composesandbox.screen.main

import edts.android.composesandbox.R
import edts.android.composesandbox.component.mainItem.MainItemState
import edts.android.composesandbox.component.search.SearchState
import edts.android.composesandbox.navigation.Destination

data class MainScreenState(
    val userName: String? = "Guest",
    val searchState: SearchState = SearchState(),
    val menuItems: List<MainItemState> = listOf(
        MainItemState(R.string.text, Destination.Text()),
        MainItemState(R.string.text_field, Destination.TextField()),
        MainItemState(R.string.button, Destination.Button()),
        MainItemState(R.string.image, Destination.Image()),
        MainItemState(R.string.dialog, Destination.Dialog()),
        MainItemState(R.string.swipe_to_refresh, Destination.SwipeToRefresh()),
        MainItemState(R.string.popup, Destination.Popup()),
        MainItemState(R.string.column, Destination.Column()),
        MainItemState(R.string.row, Destination.Row()),
    )
)
