package com.example.roomsample.di

import com.example.roomsample.domain.repoInterfaces.UserRepo
import com.example.roomsample.data.repositories.UserRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Repository module
 *
 * @constructor Create empty Repository module
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsRepo(repoImpl: UserRepoImpl): UserRepo
}
