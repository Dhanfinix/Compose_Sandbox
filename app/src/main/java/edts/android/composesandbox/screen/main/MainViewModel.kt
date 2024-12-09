package edts.android.composesandbox.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// TODO : create data store and inject it to constructor

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState>
        get() = _uiState

    fun setSearchValue(value: String){
        _uiState.update {
            it.copy(
                searchState = it.searchState.copy(
                    value = value
                )
            )
        }
    }
}