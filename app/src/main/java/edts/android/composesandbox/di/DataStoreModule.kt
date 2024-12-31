package edts.android.composesandbox.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edts.android.composesandbox.data.SettingsDataStorePreference
import edts.android.composesandbox.data.UserDataStorePreference
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStorePreference(
        @ApplicationContext context: Context,
    ): UserDataStorePreference = UserDataStorePreference(context)

    @Provides
    @Singleton
    fun provideSettingsDataStorePreference(
        @ApplicationContext context: Context,
    ): SettingsDataStorePreference = SettingsDataStorePreference(context)
}
