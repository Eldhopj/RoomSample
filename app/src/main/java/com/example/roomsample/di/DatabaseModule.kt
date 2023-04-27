package com.example.roomsample.di

import com.example.roomsample.data.local.AppDatabase
import com.example.roomsample.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao
    }

}
