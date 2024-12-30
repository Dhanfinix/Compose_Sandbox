package edts.android.composesandbox.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import edts.android.composesandbox.R
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.subtitle1

@Composable
fun EmptyStateComp(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
) {
    AnimatedVisibility(isVisible) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DotLottieAnimation(
                source = DotLottieSource.Asset("empty.lottie"),
                autoplay = true,
                loop = true
            )
            Text(
                text = stringResource(R.string.not_found),
                style = MontserratFamily.subtitle1()
            )
        }
    }
}