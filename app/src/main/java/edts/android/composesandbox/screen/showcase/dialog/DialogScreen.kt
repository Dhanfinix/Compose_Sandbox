package edts.android.composesandbox.screen.showcase.dialog

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import edts.android.composesandbox.R
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.util.LightDarkPreview
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogScreen(
    modifier: Modifier = Modifier,
    onBack: ()->Unit
) {
    val btnModifier = Modifier.fillMaxWidth()
    val context = LocalContext.current
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.dialog,
        onBack = onBack
    ) {
        item {
            var showDialog by remember { mutableStateOf(false) }
            Button(
                modifier = btnModifier,
                onClick = { showDialog = true }
            ) {
                Text("Common Dialog")
            }
            AnimatedVisibility(showDialog) {
                Dialog(
                    onDismissRequest = { showDialog = false }
                ) {
                    Card {
                        Column(Modifier.padding(24.dp)) {
                            Text("This is basic dialog that can be customized to anything we want")
                        }
                    }
                }
            }
        }
        item {
            var showDialog by remember { mutableStateOf(false) }
            Button(
                modifier = btnModifier,
                onClick = { showDialog = true }
            ) {
                Text("Undismissable Dialog")
            }
            AnimatedVisibility(showDialog) {
                Dialog(
                    onDismissRequest = { showDialog = false },
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false
                    )
                ) {
                    Card {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Click Button Below to Close")
                            Button(
                                modifier = btnModifier,
                                onClick = { showDialog = false }
                            ) {
                                Text("Close")
                            }
                        }
                    }
                }
            }
        }
        item {
            var showDialog by remember { mutableStateOf(false) }
            Button(
                modifier = btnModifier,
                onClick = { showDialog = true }
            ) {
                Text("Alert Dialog")
            }
            AnimatedVisibility(showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = { TextButton({showDialog = false}) { Text("Accept") } },
                    dismissButton = { TextButton({showDialog = false}) { Text("Dismiss") } },
                    icon = { Icon(
                        painter = painterResource(R.drawable.baseline_lock_outline_24),
                        contentDescription = null)
                    },
                    title = {
                        Image(
                            painter = painterResource(R.drawable.montains),
                            contentDescription = null)
                    },
                    text = {
                        Text("Alert dialog is dialog with predefined component position")
                    }
                )
            }
        }
        item {
            var showDialog by remember { mutableStateOf(false) }
            val pickerState = rememberDatePickerState()
            Button(
                modifier = btnModifier,
                onClick = { showDialog = true }
            ) {
                Text("Date Picker Dialog")
            }
            AnimatedVisibility(showDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton({
                            showDialog = false
                            Toast.makeText(context, formatDate(pickerState.selectedDateMillis), Toast.LENGTH_SHORT).show()
                        }) { Text("Accept") } }
                ) {
                    DatePicker(
                        state = pickerState
                    )
                }
            }
        }
        item {
            var showDialog by remember { mutableStateOf(false) }
            val duration = 3
            var countDown by remember { mutableIntStateOf(duration) }
            Button(
                modifier = btnModifier,
                onClick = {
                    showDialog = true
                    countDown = duration
                }
            ) {
                Text("Loading Dialog")
            }
            AnimatedVisibility(showDialog) {
                LaunchedEffect(showDialog) {
                    for (i in duration downTo 1){
                        delay(1000L)
                        countDown = i-1
                    }
                    showDialog = false
                }

                Dialog(
                    onDismissRequest = { showDialog = false }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                        ){
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Text(
                            "Dismissing in $countDown seconds",
                            Modifier.padding(top = 16.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

fun formatDate(milliseconds: Long?): String {
    milliseconds?.let {
        val date = Date(milliseconds)
        val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault())
        return format.format(date)
    } ?: return ""
}

@LightDarkPreview
@Composable
private fun DialogScreenPreview() {
    ComposeSandboxTheme {
        DialogScreen {  }
    }
}