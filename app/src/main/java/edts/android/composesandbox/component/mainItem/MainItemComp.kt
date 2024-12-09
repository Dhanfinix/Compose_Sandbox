package edts.android.composesandbox.component.mainItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
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
    onClick: ()->Unit
) {
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
                text = "$number. " + stringResource(state.title),
                style = InterFamily.button()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainItemPreview() {
    ComposeSandboxTheme {
        MainItemComp(
            modifier = Modifier.padding(8.dp),
            number = 7,
            state = MainItemState(R.string.text, Destination.Home())
        ){}
    }
}