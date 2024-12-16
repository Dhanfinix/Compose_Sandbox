package edts.android.composesandbox.screen.showcase.swipe_to_refresh

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.component.ShimmerComp
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToRefreshScreen(
    modifier: Modifier = Modifier,
    viewModel: SwipeToRefreshViewModel,
    onBack: ()->Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        uiState.isRefreshing,
        {
            viewModel.setRefreshState(true)
        }
    )
    LaunchedEffect(uiState.isRefreshing) {
        if (uiState.isRefreshing){
            delay(3000)
            viewModel.setRefreshState(false)
        }
    }
    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.swipe_to_refresh,
        onBack = onBack,
        boxContent = {
            LazyColumn(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
            ) {
                for (i in 0..30){
                    item{
                        if (uiState.isRefreshing){
                            ShimmerComp(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                height = 20.dp
                            )
                        } else {
                            Text(
                                text = if (i == 0)
                                    "Scroll to top of the list to trigger swipe to refresh, " +
                                            "the loading will appear for 3 seconds." +
                                            "While loading, shimmer will appear to all items."
                                else
                                    "This is item number $i",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = uiState.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }
    )
}