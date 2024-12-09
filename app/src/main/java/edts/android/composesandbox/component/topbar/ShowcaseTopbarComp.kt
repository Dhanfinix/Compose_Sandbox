package edts.android.composesandbox.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import edts.android.composesandbox.R
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.headline3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseTopbarComp(
    modifier: Modifier = Modifier,
    title: Int,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onBack:()->Unit
) {
    MediumTopAppBar(
        modifier= modifier,
        title = {
            Text(
                text = stringResource(title),
                style = MontserratFamily.headline3()
            )
        },
        navigationIcon = {
            IconButton(onBack) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TopbarCompPreview() {
    ComposeSandboxTheme {
        ShowcaseTopbarComp(
            title = R.string.app_name
        ) { }
    }
}