package com.example.roomsample.data.repositories

import com.example.roomsample.data.local.dao.UserDao
import com.example.roomsample.domain.repoInterfaces.UserRepo
import com.example.roomsample.domain.model.User
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


@ViewModelScoped
class UserRepoImpl @Inject constructor(private val userDao: UserDao) : UserRepo {

    override val readAllData: Flow<List<User>> = userDao.readAllData()

    override suspend fun addUser(user: User){
        userDao.upsertUser(user)
    }

    override suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    override suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}
