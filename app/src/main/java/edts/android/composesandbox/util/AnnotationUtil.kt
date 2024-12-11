package edts.android.composesandbox.util

import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Mode", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
annotation class LightDarkPreview