package edts.android.composesandbox.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edts.android.composesandbox.component.theme_button.ThemeMode
import edts.android.composesandbox.data.SettingsDataStorePreference
import edts.android.composesandbox.data.UserDataStorePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @param dataStore is nullable to accommodate the preview in screen,
 * because the datastore preference need application context which can't
 * be obtained via preview.
 */

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val dataStore: UserDataStorePreference,
        private val settingsDataStore: SettingsDataStorePreference,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(MainScreenState())
        val uiState: StateFlow<MainScreenState>
            get() = _uiState

        fun setSearchValue(value: String) {
            _uiState.update {
                it.copy(
                    searchState =
                        it.searchState.copy(
                            value = value,
                        ),
                )
            }
        }

        init {
            getUsername()
            getSortType()
        }

        fun setDialogVisibility(isVisible: Boolean) {
            _uiState.update {
                it.copy(
                    isChangeNameDialogVisible = isVisible,
                )
            }
        }

        fun saveUsername(value: String) =
            viewModelScope.launch {
                dataStore.saveUsername(value)
            }

        private fun setUsername(value: String) {
            _uiState.update {
                it.copy(
                    userName = value,
                )
            }
        }

        private fun getUsername() =
            viewModelScope.launch {
                dataStore.getUsername().collectLatest { name ->
                    setUsername(name)
                }
            }

        private fun getSortType() =
            viewModelScope.launch {
                settingsDataStore.getSortType().collectLatest { type ->
                    _uiState.update {
                        it.copy(
                            sortType = type,
                        )
                    }
                }
            }

        fun changeSortType(sortType: SortType) {
            _uiState.update {
                it.copy(
                    sortType = sortType,
                )
            }
            viewModelScope.launch {
                settingsDataStore.saveSortType(sortType)
            }
        }

        fun changeTheme(themeMode: ThemeMode) {
            viewModelScope.launch {
                settingsDataStore.saveThemeMode(themeMode)
            }
        }
    }
