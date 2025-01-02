package edts.android.composesandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edts.android.composesandbox.navigation.LocalNavController
import edts.android.composesandbox.navigation.LocalTheme
import edts.android.composesandbox.navigation.NavigationHost
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<MainActivityViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            ComposeSandboxTheme(
                mode = uiState.theme,
            ) {
                CompositionLocalProvider(
                    LocalNavController provides rememberNavController(),
                    LocalTheme provides uiState.theme,
                ) {
                    NavigationHost()
                }
            }
        }
    }
}
