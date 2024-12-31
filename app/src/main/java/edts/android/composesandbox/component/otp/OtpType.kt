package edts.android.composesandbox.component.otp

import edts.android.composesandbox.ui.theme.ColorNeutral10
import edts.android.composesandbox.ui.theme.ColorNeutral30
import edts.android.composesandbox.ui.theme.ColorNeutral40
import edts.android.composesandbox.ui.theme.ColorNeutral70
import edts.android.composesandbox.ui.theme.ColorRed10
import edts.android.composesandbox.ui.theme.ColorRed30
import edts.android.composesandbox.ui.theme.Purple40

enum class OtpType {
    NORMAL,
    ERROR {
        override val containerColor = ColorRed10
        override val contentColor = ColorRed30
        override val borderColor = ColorRed30
        override val focusedBorderColor = ColorNeutral40
    }, ;

    open val containerColor = ColorNeutral10
    open val borderColor = ColorNeutral30
    open val activeBorderColor = Purple40
    open val contentColor = ColorNeutral70
    open val focusedBorderColor = ColorNeutral40
}
