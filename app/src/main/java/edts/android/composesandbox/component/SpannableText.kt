package edts.android.composesandbox.component

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.body1

/**
 * Inspired from these gists
 * https://gist.github.com/shmehdi01/d1a1d60becc6397a763a6f139af112d8
 * https://joebirch.co/android/migrating-from-the-clickabletext-composable-to-linkannotation/
 */
@Composable
fun SpannableText(
    modifier: Modifier = Modifier,
    text: String,
    spanText: String,
    afterSpanText: String? = "",
    spanStyle: TextLinkStyles = TextLinkStyles(
        SpanStyle(
            color = Color.Blue,
            textDecoration = TextDecoration.Underline
        )
    ),
    style: TextStyle = MontserratFamily.body1(),
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Unspecified,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (String) -> Unit,
) {

    val annotated = buildAnnotatedString {
        append("$text ")
        withLink(
            LinkAnnotation.Clickable(spanText, spanStyle){
                onClick(spanText)
            },
        ){
            append(spanText)
        }
        append(" $afterSpanText")
    }
    Text(
        text = annotated,
        modifier = modifier,
        style = style,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        textAlign = textAlign
    )
}

@Preview
@Composable
private fun PreviewSpannableText() {
    ComposeSandboxTheme {
        Surface {
            SpannableText(
                text = "Muhammad",
                spanText = "Ramdhan",
                afterSpanText = ", S.Pd"
            ) {}
        }
    }
}