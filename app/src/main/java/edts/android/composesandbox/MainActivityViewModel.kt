package edts.android.composesandbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edts.android.composesandbox.data.SettingsDataStorePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
    @Inject
    constructor(
        private val settingsDataStorePreference: SettingsDataStorePreference,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(MainActivityState())
        val uiState: StateFlow<MainActivityState>
            get() = _uiState

        init {
            getTheme()
        }

        private fun getTheme() {
            viewModelScope.launch {
                settingsDataStorePreference.getThemeMode().collectLatest { savedTheme ->
                    _uiState.update {
                        it.copy(
                            theme = savedTheme,
                        )
                    }
                }
            }
        }
    }
