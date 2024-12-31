package edts.android.composesandbox.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BaseDataStorePreference {
    abstract val dataStore: DataStore<Preferences>

    protected suspend fun <T> savePreference(
        key: Preferences.Key<T>,
        value: T,
    ) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

    protected fun <T> getPreference(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> = dataStore.data.map { preferences -> preferences[key] ?: defaultValue }
}