package edts.android.composesandbox.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home()
    ){
        composable<Destination.Home> {
            MainScreen{
                navController.navigate(it){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                }
            }
        }
        composable<Destination.Text> {
            TextScreen()
            NavBackHandler(navController)
        }
    }
}

@Composable
fun NavBackHandler(
    navController: NavHostController
) {
    BackHandler {
        navController.navigate(Destination.Home()) {
            popUpTo(navController.graph.findStartDestination().id)
            restoreState = true
            launchSingleTop = true
        }
    }
}