package com.example.roomsample.domain.repoInterfaces

import com.example.roomsample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    val readAllData: Flow<List<User>>
    suspend fun updateUser(user: User)
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun deleteAllUsers()
}
