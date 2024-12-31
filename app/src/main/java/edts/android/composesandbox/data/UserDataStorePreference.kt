package edts.android.composesandbox.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import edts.android.composesandbox.data.PreferencesKeys.USERNAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "userdata")

class UserDataStorePreference(
    context: Context,
) : BaseDataStorePreference() {
    override val dataStore = context.userDataStore

    suspend fun saveUsername(name: String) = savePreference(USERNAME, name)

    fun getUsername(defaultValue: String = "Guest") = getPreference(USERNAME, defaultValue)
}
