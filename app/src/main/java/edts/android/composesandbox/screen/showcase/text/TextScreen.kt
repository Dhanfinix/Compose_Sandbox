package edts.android.composesandbox.screen.showcase.text

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edts.android.composesandbox.R
import edts.android.composesandbox.component.SpannableText
import edts.android.composesandbox.screen.showcase.base.ShowcaseBaseScreen
import edts.android.composesandbox.ui.theme.ComposeSandboxTheme
import edts.android.composesandbox.ui.theme.InterFamily
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.Purple40
import edts.android.composesandbox.ui.theme.body1
import edts.android.composesandbox.util.LightDarkPreview

@Composable
fun TextScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var expandEllipsis by remember { mutableStateOf(false) }

    ShowcaseBaseScreen(
        modifier = modifier,
        title = R.string.text
    ){
        item {
            // normal text
            Text("Normal Text")
        }
        item {
            // text with montserrat style
            Text(
                text = "Text with montserrat family",
                style = MontserratFamily.body1()
            )
        }
        item {
            // text with inter style
            Text(
                text = "Text with inter family",
                style = InterFamily.body1()
            )
        }
        item {
            // text color
            Text(
                text = "[Copy Method] Text with montserrat family and green color",
                style = MontserratFamily.body1().copy(
                    color = Color.Green
                )
            )
        }
        item {
            Text(
                text = "[Parameter] Text with montserrat family and red color",
                style = MontserratFamily.body1(),
                color = Color.Red
            )
        }
        item {
            // italic
            Text(
                text = "Text with inter family italic",
                style = InterFamily.body1(),
                fontStyle = FontStyle.Italic
            )
        }
        item {
            // bold
            Text(
                text = "Text with montserrat family bold",
                style = MontserratFamily.body1(),
                fontWeight = FontWeight.Bold
            )
        }
        item {
            // shadow
            val offset = Offset(5.0f, 10.0f)
            Text(
                text = "Text with inter family and blue shadow",
                style = InterFamily.body1().copy(
                    shadow = Shadow(
                        color = Color.Blue,
                        offset = offset,
                        blurRadius = 3f
                    )
                )
            )
        }
        item {
            // gradient color
            val gradientColors = listOf(Cyan, Magenta, Purple40)
            Text(
                text = "This is text with gradient color",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )
        }
        item {
            // marquee
            Text(
                text = "This marquee text, it's long, so it need to be scrolled by marquee modifier method",
                modifier = Modifier.basicMarquee()
            )
        }
        item {
            // ellipsis
            Text(
                text = "This is also long text, we use ellipsis to show that the text isn't done yet",
                maxLines = if (expandEllipsis) Int.MAX_VALUE else 1,
                overflow =
                if (expandEllipsis)
                    TextOverflow.Visible
                else
                    TextOverflow.Ellipsis,
                modifier = Modifier.clickable {
                    expandEllipsis = !expandEllipsis
                }
            )
        }
        item {
            // text with underline
            Text(
                text = "Text with underline",
                textDecoration = TextDecoration.Underline
            )
        }
        item {
            // text with LineThrough
            Text(
                text = "Text with LineThrough",
                textDecoration = TextDecoration.LineThrough
            )
        }
        item {
            // text with link
            SpannableText(
                text = "This is",
                spanText = "spannable",
                afterSpanText = "text"
            ) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        item {
            // text with different fonts
            Text(
                text = buildAnnotatedString {
                    append("This text uses ")
                    withStyle(style = SpanStyle(
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Bold
                    )) {
                        append("Inter font")
                    }
                    append(" and this text uses ")
                    withStyle(style = SpanStyle(
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.Bold
                    )) {
                        append("Montserrat font")
                    }
                    append(".")
                },
                style = TextStyle(fontSize = 16.sp)
            )
        }
        item {
            // Text with Subscript and Superscript
            Text(
                text = buildAnnotatedString {
                    append("Below is text with sub/super script\n")
                    append("H")
                    withStyle(style = SpanStyle(baselineShift = BaselineShift.Subscript)) {
                        append("2")
                    }
                    append("O is water and E = mc")
                    withStyle(style = SpanStyle(baselineShift = BaselineShift.Superscript)) {
                        append("2")
                    }
                    append(" is a famous equation.")
                },
                style = MontserratFamily.body1()
            )
        }
        item {
            // text with spacing spacing
            Text(
                text = "Text with letter spacing",
                style = MontserratFamily.body1().copy(
                    letterSpacing = 2.sp
                )
            )
        }
        item {
            Text(
                text = "Text with background color",
                style = MontserratFamily.body1(),
                color = Color.White,
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(4.dp)
            )
        }
        item {
            // cdata html string text
            Text(
                text = AnnotatedString.Companion
                    .fromHtml(
                        htmlString = stringResource(R.string.cdata_string),
                        linkStyles = TextLinkStyles(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun TextScreenPreview() {
    ComposeSandboxTheme {
        TextScreen()
    }
}