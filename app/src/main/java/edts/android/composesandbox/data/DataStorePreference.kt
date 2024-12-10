package edts.android.composesandbox.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import edts.android.composesandbox.data.PreferencesKeys.USERNAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userdata")

class DataStorePreference(
    context: Context
) {
    private val dataStore = context.dataStore
    private suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

    private fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map { preferences -> preferences[key] ?: defaultValue }
    }

    suspend fun saveUsername(name: String) = savePreference(USERNAME, name)

    fun getUsername(
        defaultValue: String = "Guest"
    ) = getPreference(USERNAME, defaultValue)
}