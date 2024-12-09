package edts.android.composesandbox.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun CardCornerCutComp(
    modifier: Modifier = Modifier,
    onClick: ()->Unit = {},
    content: @Composable ()->Unit
) {
    val shape = CutCornerShape(topStartPercent = 50, bottomEndPercent = 50)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .clickable { onClick() },
        shape = shape
    ){
        content()
    }
}