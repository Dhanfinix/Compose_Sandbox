package edts.android.composesandbox.screen.showcase.base

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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseBaseScreen(
    modifier: Modifier = Modifier,
    title: Int,
    verticalArrangement : Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalAllignment: Alignment.Horizontal = Alignment.Start,
    fab: @Composable ()->Unit = {},
    onBack: ()->Unit,
    boxContent: (@Composable BoxScope.()->Unit)? = null,
    content: (LazyListScope.()->Unit)? = null
) {
    val scrollBehavior = exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            ShowcaseTopbarComp(
                title = title,
                scrollBehavior = scrollBehavior
            ){ onBack() }
        },
        floatingActionButton = fab
    ) { innerPadding ->
        if (boxContent != null && content != null){
            throw IllegalArgumentException("Only one of boxContent or content should be provided.")
        }
        if (boxContent != null){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(innerPadding)
            ) {
                boxContent()
            }
        } else if (content != null){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAllignment
            ) {
                content()
            }
        }
    }
}