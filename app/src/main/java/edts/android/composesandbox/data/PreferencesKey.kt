package edts.android.composesandbox.data

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val USERNAME = stringPreferencesKey("USERNAME")
    val SORT_TYPE = intPreferencesKey("SORT_TYPE")
}
