package edts.android.composesandbox.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edts.android.composesandbox.screen.main.SortType
import edts.android.composesandbox.ui.theme.MontserratFamily
import edts.android.composesandbox.ui.theme.subtitle1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheetComp(
    modifier: Modifier = Modifier,
    showSort: Boolean,
    sortType: SortType,
    onChanged: (SortType)->Unit,
    onDismiss: ()->Unit
) {
    AnimatedVisibility(showSort) {
        ModalBottomSheet(
            modifier = modifier,
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Sort By",
                    style = MontserratFamily.subtitle1(),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable { onChanged(SortType.CREATED) }
                ) {
                    RadioButton(
                        selected = (sortType == SortType.CREATED),
                        onClick = {}
                    )
                    Text(SortType.CREATED.toString())
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable { onChanged(SortType.ALPHABET) }
                ){
                    RadioButton(
                        selected = (sortType == SortType.ALPHABET),
                        onClick = {}
                    )
                    Text(SortType.ALPHABET.toString())
                }
            }
        }
    }
}