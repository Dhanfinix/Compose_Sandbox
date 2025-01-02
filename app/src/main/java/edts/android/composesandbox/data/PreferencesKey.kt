package edts.android.composesandbox.data

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    // userdata
    val USERNAME = stringPreferencesKey("USERNAME")

    // settings
    val SORT_TYPE = intPreferencesKey("SORT_TYPE")
    val THEME_MODE = stringPreferencesKey("THEME_MODE")
}
