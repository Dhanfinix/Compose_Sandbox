package edts.android.composesandbox.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import edts.android.composesandbox.screen.main.MainScreen
import edts.android.composesandbox.screen.main.MainViewModel
import edts.android.composesandbox.screen.showcase.button.ButtonScreen
import edts.android.composesandbox.screen.showcase.button.ButtonViewModel
import edts.android.composesandbox.screen.showcase.column.ColumnScreen
import edts.android.composesandbox.screen.showcase.column.RegularColumnScreen
import edts.android.composesandbox.screen.showcase.dialog.DialogScreen
import edts.android.composesandbox.screen.showcase.image.ImageScreen
import edts.android.composesandbox.screen.showcase.popup.PopupScreen
import edts.android.composesandbox.screen.showcase.swipe_to_refresh.SwipeToRefreshScreen
import edts.android.composesandbox.screen.showcase.swipe_to_refresh.SwipeToRefreshViewModel
import edts.android.composesandbox.screen.showcase.text.TextScreen
import edts.android.composesandbox.screen.showcase.textfield.TextFieldScreen
import edts.android.composesandbox.screen.showcase.textfield.TextFieldViewModel

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    CompositionLocalProvider(
        LocalNavController provides rememberNavController()
    ) {
        val navController = LocalNavController.current
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Destination.Home()
        ){
            composable<Destination.Home> {
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(viewModel = mainViewModel){
                    try {
                        navController.navigate(it){
                            popUpTo(navController.graph.findStartDestination().id){
                                // save main screen state
                                saveState = true
                            }
                        }
                    } catch (e: IllegalArgumentException){
                        Toast.makeText(context, "Feature not ready yet", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            composable<Destination.Text> {
                TextScreen()
            }
            composable<Destination.TextField> {
                val viewModel = hiltViewModel<TextFieldViewModel>()
                TextFieldScreen(viewModel = viewModel)
            }
            composable<Destination.Button> {
                val viewModel = hiltViewModel<ButtonViewModel>()
                ButtonScreen(viewModel = viewModel)
            }
            composable<Destination.ImageShape> {
                ImageScreen()
            }
            composable<Destination.Dialog> {
                DialogScreen()
            }
            composable<Destination.SwipeToRefresh> {
                val viewModel = hiltViewModel<SwipeToRefreshViewModel>()
                SwipeToRefreshScreen(viewModel = viewModel)
            }
            composable<Destination.Popup> {
                PopupScreen()
            }
            composable<Destination.Column> {
                ColumnScreen()
            }
            composable<Destination.RegularColumn> {
                RegularColumnScreen()
            }
        }
    }
}
