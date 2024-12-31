package edts.android.composesandbox.component.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.R
import edts.android.composesandbox.component.KeyboardVisibilityListener
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.body1

@Composable
fun SearchComp(
    modifier: Modifier = Modifier,
    state: SearchState,
    hint: String = "Input search keyword",
    delegate: SearchDelegate? = null,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isKeyboardVisible by remember { mutableStateOf(false) }

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            focusRequester.requestFocus()
        }
    }

    KeyboardVisibilityListener {
        isKeyboardVisible = it
        if (!isKeyboardVisible) {
            delegate?.onClose()
            isExpanded = false
        }
    }

    Box {
        AnimatedContent(isExpanded, label = "search") {
            if (!it) {
                SearchButtonComp { isExpanded = !isExpanded }
            } else {
                BasicTextField(
                    modifier =
                        modifier
                            .focusRequester(focusRequester)
                            .background(Color.White, RoundedCornerShape(100)),
                    value = state.value,
                    textStyle = MontserratFamily.body1(),
                    onValueChange = { newValue ->
                        delegate?.onChange(newValue)
                    },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                        ),
                    keyboardActions =
                        KeyboardActions(
                            onDone = { focusManager.clearFocus() },
                        ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            SearchButtonComp { isExpanded = !isExpanded }
                            Box(
                                Modifier.weight(1f),
                            ) {
                                androidx.compose.animation
                                    .AnimatedVisibility(
                                        visible = state.value.isEmpty(),
                                    ) {
                                        Text(
                                            text = hint,
                                            style = MontserratFamily.body1(),
                                            color = Color.LightGray,
                                        )
                                    }
                                innerTextField()
                            }

                            AnimatedVisibility(state.value.isNotEmpty()) {
                                IconButton(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    onClick = { delegate?.onChange("") },
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.baseline_close_24),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
                                        contentDescription = "Clear Input",
                                    )
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun SearchButtonComp(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = { onClick() },
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_search_24),
            contentDescription = "Search",
        )
    }
}

@Preview
@Composable
private fun SearchCompPreview(
    @PreviewParameter(SearchParamProvider::class)
    inputText: String,
) {
    ComposeSandboxTheme {
        SearchComp(
            state = SearchState(value = inputText),
        )
    }
}

private class SearchParamProvider : PreviewParameterProvider<String> {
    override val values =
        sequenceOf(
            "",
            "Button",
        )
}
