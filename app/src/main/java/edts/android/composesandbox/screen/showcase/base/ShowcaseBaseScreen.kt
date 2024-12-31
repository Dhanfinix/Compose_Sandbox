package edts.android.composesandbox.screen.showcase.base

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import edts.android.composesandbox.navigation.Destination
import edts.android.composesandbox.navigation.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseBaseScreen(
    modifier: Modifier = Modifier,
    title: Int,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    fab: @Composable () -> Unit = {},
    boxContent: (@Composable BoxScope.() -> Unit)? = null,
    content: (LazyListScope.() -> Unit)? = null,
) {
    val scrollBehavior = exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier =
            modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
        topBar = {
            ShowcaseTopbarComp(
                title = title,
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = fab,
    ) { innerPadding ->
        if (boxContent != null && content != null) {
            throw IllegalArgumentException("Only one of boxContent or content should be provided.")
        }
        val contentModifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        if (boxContent != null) {
            Box(
                modifier = contentModifier,
            ) {
                boxContent()
            }
        } else if (content != null) {
            LazyColumn(
                modifier = contentModifier,
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
            ) {
                content()
            }
        }
    }
    NavBackHandler()
}

/**
 * This component is used to handle on back press to previous screen
 * using native back button of the phone
 */
@Composable
fun NavBackHandler() {
    val navController = LocalNavController.current
    val context = LocalContext.current
    BackHandler {
        val previousBackStackEntry = navController.previousBackStackEntry
        if (previousBackStackEntry
                ?.destination
                ?.route
                ?.contains(Destination.Home()::class.qualifiedName ?: "") == true
        ) {
            Toast.makeText(context, "Back Home", Toast.LENGTH_SHORT).show()
            navController.navigate(Destination.Home()) {
                popUpTo(navController.graph.findStartDestination().id)
                restoreState = true
                launchSingleTop = true
            }
        } else {
            navController.navigateUp()
        }
    }
}
