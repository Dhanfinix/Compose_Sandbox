package edts.android.composesandbox.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import edts.android.composesandbox.component.theme_button.ThemeMode
import edts.android.composesandbox.data.PreferencesKeys.SORT_TYPE
import edts.android.composesandbox.data.PreferencesKeys.THEME_MODE
import edts.android.composesandbox.screen.main.SortType
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStorePreference(
    context: Context,
) : BaseDataStorePreference() {
    override val dataStore = context.settingsDataStore

    suspend fun saveSortType(sortType: SortType) = savePreference(SORT_TYPE, sortType.ordinal)

    suspend fun saveThemeMode(themeMode: ThemeMode) = savePreference(THEME_MODE, themeMode.value)

    fun getSortType() = getPreference(SORT_TYPE, SortType.CREATED.ordinal)

    fun getThemeMode() =
        getDatastoreData().map { pref ->
            ThemeMode.fromString(pref[THEME_MODE] ?: ThemeMode.System.value)
        }
}
