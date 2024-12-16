package edts.android.composesandbox.screen.showcase.swipe_to_refresh

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SwipeToRefreshViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SwipeToRefreshState())
    val uiState: StateFlow<SwipeToRefreshState>
        get() = _uiState

    fun setRefreshState(isRefreshing: Boolean){
        _uiState.update {
            it.copy(
                isRefreshing = isRefreshing
            )
        }
    }
}