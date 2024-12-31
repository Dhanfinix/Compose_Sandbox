package edts.android.composesandbox.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import edts.android.composesandbox.data.PreferencesKeys.SORT_TYPE
import edts.android.composesandbox.screen.main.SortType

private val Context.settingsDataStore : DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStorePreference(
    context: Context
) : BaseDataStorePreference() {
    override val dataStore = context.settingsDataStore

    suspend fun saveSortType(sortType: SortType) = savePreference(SORT_TYPE, sortType.ordinal)

    fun getSortType() = getPreference(SORT_TYPE, SortType.CREATED.ordinal)
}