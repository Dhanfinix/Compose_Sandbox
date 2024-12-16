package edts.android.composesandbox.screen.showcase.column

data class ColumnData(
    val type: ColumnType,
    val value: String
)

enum class ColumnType{
    TEXT, IMAGE, BUTTON
}