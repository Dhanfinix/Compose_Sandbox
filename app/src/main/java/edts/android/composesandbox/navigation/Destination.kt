package edts.android.composesandbox.navigation
import kotlinx.serialization.Serializable

sealed class Destination {
    abstract val route : String

    @Serializable
    data class Home(
        override val route: String = "HomeRoute"
    ) : Destination()

    @Serializable
    data class Text(
        override val route: String = "TextRoute"
    ) : Destination()

    @Serializable
    data class TextField(
        override val route: String = "TextFieldRoute"
    ) : Destination()

    @Serializable
    data class Button(
        override val route: String = "ButtonRoute"
    ) : Destination()

    @Serializable
    data class ImageShape(
        override val route: String = "ImageShapeRoute"
    ) : Destination()

    @Serializable
    data class Dialog(
        override val route: String = "DialogRoute"
    ) : Destination()

    @Serializable
    data class SwipeToRefresh(
        override val route: String = "SwipeToRefreshRoute"
    ) : Destination()

    @Serializable
    data class Popup(
        override val route: String = "PopupRoute"
    ) : Destination()

    @Serializable
    data class Column(
        override val route: String = "ColumnRoute"
    ) : Destination()

    @Serializable
    data class RegularColumn(
        override val route: String = "RegularColumnRoute"
    ) : Destination()

    @Serializable
    data class Row(
        override val route: String = "RowRoute"
    ) : Destination()

    @Serializable
    data class BottomSheet(
        override val route: String = "BottomSheetRoute"
    ) : Destination()

    @Serializable
    data class Dropdown(
        override val route: String = "DropdownRoute"
    ) : Destination()
}