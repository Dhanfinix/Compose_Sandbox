package edts.android.composesandbox.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edts.android.composesandbox.screen.main.MainScreen
import edts.android.composesandbox.screen.showcase.text.TextScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home()
    ){
        composable<Destination.Home> {
            MainScreen{
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
            TextScreen{ navController.navigateUp() }
            NavBackHandler(navController)
        }
    }
}

/**
 * This component is used to handle on back press
 */
@Composable
fun NavBackHandler(
    navController: NavHostController
) {
    BackHandler {
        navController.navigate(Destination.Home()) {
            popUpTo(navController.graph.findStartDestination().id)
            // restore main screen state
            restoreState = true
            launchSingleTop = true
        }
    }
}