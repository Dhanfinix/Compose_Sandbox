package edts.android.composesandbox.screen.showcase.textfield

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TextFieldViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiState = MutableStateFlow(TextFieldScreenState())
        val uiState: StateFlow<TextFieldScreenState>
            get() = _uiState

        fun setNormal(value: String) {
            _uiState.update {
                it.copy(
                    normalTextField = value,
                )
            }
        }

        fun setOutlined(value: String) {
            _uiState.update {
                it.copy(
                    outlinedTextField = value,
                )
            }
        }

        fun setBasic(value: String) {
            _uiState.update {
                it.copy(
                    basicTextField = value,
                )
            }
        }

        fun setPassword(value: String) {
            _uiState.update {
                it.copy(
                    passwordTextField = value,
                )
            }
        }

        fun setSearchQuery(value: String) {
            _uiState.update {
                it.copy(
                    searchState =
                        it.searchState.copy(
                            value = value,
                        ),
                )
            }
        }

        fun setOtpValue(value: String) {
            _uiState.update {
                it.copy(
                    pinTextField =
                        it.pinTextField.copy(
                            otpText = value,
                        ),
                )
            }
        }

        fun setCounter(value: String) {
            _uiState.update {
                it.copy(
                    counterTextField =
                        it.counterTextField.copy(
                            value = value,
                        ),
                )
            }
        }
    }
