package edts.android.composesandbox.component.mainItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.util.StringUtil.highlightQuery
import edts.android.composesandbox.component.CardCornerCutComp
import edts.android.composesandbox.navigation.Destination
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.InterFamily
import edts.android.composesandbox.ui.theme.button

@Composable
fun MainItemComp(
    modifier: Modifier = Modifier,
    number: Int,
    state: MainItemState,
    highlight: String = "",
    onClick: ()->Unit
) {
    val normal = "$number. " + stringResource(state.title)
    val highlighted = normal.highlightQuery(highlight)

    CardCornerCutComp(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Row(
            Modifier.padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = highlighted,
                style = InterFamily.button()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainItemPreview(
    @PreviewParameter(MainItemPreviewProvider::class)
    highlight: String
) {
    ComposeSandboxTheme {
        MainItemComp(
            modifier = Modifier.padding(8.dp),
            number = 7,
            state = MainItemState(R.string.swipe_to_refresh, Destination.Home()),
            highlight = highlight
        ){}
    }
}

private class MainItemPreviewProvider: PreviewParameterProvider<String?>{
    override val values = sequenceOf(
        "",
        "Swipe",
        "Refresh"
    )
}