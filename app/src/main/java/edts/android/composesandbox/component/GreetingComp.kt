package edts.android.composesandbox.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import edts.android.composesandbox.R
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.body1
import edts.android.composesandbox.ui.theme.headline1
import edts.android.composesandbox.ui.theme.headline3

@Composable
fun GreetingComp(
    modifier: Modifier = Modifier,
    name: String?,
    collapsedFraction: Float    // 0F expand, 1F collapse
) {
    val currentFraction by rememberUpdatedState(newValue = collapsedFraction)
    val isCollapsing = currentFraction < 1f
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AnimatedVisibility(isCollapsing) {
            Text(
                text = "Hello $name!",
                style = MontserratFamily.body1()
            )
        }
        Text(
            text = stringResource(R.string.app_name),
            style = if (isCollapsing)
                MontserratFamily.headline1()
            else
                MontserratFamily.headline3()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(
    @PreviewParameter(GreetingParamProvider::class) collapsedFraction: Float
) {
    ComposeSandboxTheme {
        GreetingComp(
            name = "Guest",
            collapsedFraction = collapsedFraction
        )
    }
}

private class GreetingParamProvider: PreviewParameterProvider<Float>{
    override val values = sequenceOf(
        0F, 1F
    )
}