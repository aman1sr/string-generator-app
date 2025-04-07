package com.aman.randomstringgenerator.di
import android.content.Context
import androidx.room.Room
import com.aman.randomstringgenerator.data.database.AppDatabase
import com.aman.randomstringgenerator.data.database.RandomStringDao
import com.aman.randomstringgenerator.helper.DefaultDispatcherProvider
import com.aman.randomstringgenerator.helper.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "random_string_db"
    ).build()

    @Provides
    fun provideRandomStringDao(appDatabase: AppDatabase): RandomStringDao {
        return appDatabase.randomStringDao()
    }
}