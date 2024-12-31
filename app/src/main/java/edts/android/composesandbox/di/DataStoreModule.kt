package edts.android.composesandbox.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edts.android.composesandbox.data.DataStorePreference
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStorePreference(
        @ApplicationContext context: Context,
    ): DataStorePreference = DataStorePreference(context)
}
