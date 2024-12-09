package edts.android.composesandbox.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edts.android.composesandbox.component.GreetingComp
import edts.android.composesandbox.component.mainItem.MainItemComp
import edts.android.composesandbox.component.search.SearchComp
import edts.android.composesandbox.navigation.Destination
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    onNavigate: (Destination)->Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = exitUntilCollapsedScrollBehavior(topAppBarState)
    val uiState by viewModel.uiState.collectAsState()
    val filteredItems = uiState.menuItems.filter {
        stringResource(it.title)
            .lowercase()
            .contains(uiState.searchState.value)
    }

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            LargeTopAppBar(
                title = {
                    GreetingComp(
                        name = uiState.userName,
                        collapsedFraction = topAppBarState.collapsedFraction
                    )
                },
                actions = {
                    SearchComp(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        state = uiState.searchState
                    ) {
                        viewModel.setSearchValue(it)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            itemsIndexed(filteredItems){index, item->
                val itemModifier = if(index == 0)
                    Modifier.padding(top = 8.dp)
                else Modifier
                MainItemComp(
                    modifier = itemModifier.padding(bottom = 8.dp),
                    number = index+1,
                    state = item
                ){
                    onNavigate(item.route)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    ComposeSandboxTheme {
        MainScreen{}
    }
}