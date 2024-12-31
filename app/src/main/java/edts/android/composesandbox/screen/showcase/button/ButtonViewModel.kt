package edts.android.composesandbox.screen.showcase.button

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ButtonViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiState = MutableStateFlow(ButtonScreenState())
        val uiState: StateFlow<ButtonScreenState>
            get() = _uiState

        fun setSegmentedChecked(
            index: Int,
            isMulti: Boolean,
        ) {
            _uiState.update { currentState ->
                if (isMulti) {
                    val updatedOptions =
                        currentState.multiSegmentedState.options
                            .mapIndexed { i, option ->
                                if (i == index) {
                                    option.copy(checked = !option.checked)
                                } else {
                                    option
                                }
                            }
                    currentState.copy(
                        multiSegmentedState =
                            currentState.multiSegmentedState.copy(
                                options = updatedOptions,
                            ),
                    )
                } else {
                    currentState.copy(
                        singleSegmentedState =
                            currentState.singleSegmentedState.copy(
                                selectedIndex = index,
                            ),
                    )
                }
            }
        }
    }
