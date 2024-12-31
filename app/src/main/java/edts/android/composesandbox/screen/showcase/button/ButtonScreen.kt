package edts.android.composesandbox.screen.showcase.button

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.component.segment_button.SegmentedButtonComp
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.util.LightDarkPreview

@Composable
fun ButtonScreen(
    modifier: Modifier = Modifier,
    viewModel: ButtonViewModel,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.button,
        fab = {
            FloatingActionButton(
                onClick = { showToast(context, "FAB") },
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = null,
                )
            }
        },
    ) {
        item {
            // normal button
            Button(
                onClick = { showToast(context, "Normal") },
            ) {
                Text("Normal")
            }
        }
        item {
            // elevated button
            ElevatedButton(
                onClick = { showToast(context, "Elevated") },
            ) {
                Text("Elevated")
            }
        }
        item {
            // multi select segmented button
            SegmentedButtonComp(
                uiState = uiState.multiSegmentedState,
            ) {
                viewModel.setSegmentedChecked(it, true)
            }
        }
        item {
            // Single select segmented button
            SegmentedButtonComp(
                uiState = uiState.singleSegmentedState,
            ) {
                viewModel.setSegmentedChecked(it, false)
            }
        }
        // Anything with clickable modifier
        item {
            Text(
                text = "Anything can also become a button, including text",
                modifier = Modifier.clickable { showToast(context, "Text") },
            )
        }
        item {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "This image also clickable",
                modifier = Modifier.clickable { showToast(context, "Also this image") },
            )
        }
        item {
            TextField(
                modifier = Modifier.clickable { showToast(context, "Text Field as Button!") },
                enabled = false,
                label = { Text("As long as it has clickable modifier") },
                value = "",
                onValueChange = {},
            )
        }
        item {
            HorizontalDivider(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable { showToast(context, "Even a Divider") },
                thickness = 4.dp,
                color = Color.Red,
            )
        }
    }
}

private fun showToast(
    context: Context,
    message: String,
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@LightDarkPreview
@Composable
private fun ButtonScreenPreview() {
    ComposeSandboxTheme {
        ButtonScreen(viewModel = ButtonViewModel())
    }
}
