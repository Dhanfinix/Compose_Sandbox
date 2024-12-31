package edts.android.composesandbox.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.util.shimmer

@Composable
fun ShimmerComp(
    modifier: Modifier = Modifier,
    height: Dp = 15.dp,
) {
    Box(
        modifier
            .height(height)
            .shimmer(),
    )
}
