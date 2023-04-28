package com.example.roomsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomsample.data.local.dao.UserDao
import com.example.roomsample.data.local.typeconverters.DateConverter
import com.example.roomsample.domain.model.User

/**
 * App database
 *
 * @constructor Create empty App database
 */
@Database(
    version = 1,
    entities = [User::class], //Add entities in here
    exportSchema = false
)
@TypeConverters(DateConverter::class) // Add type converters in here
abstract class AppDatabase : RoomDatabase() {
    // Add Dao's in here
    abstract val userDao: UserDao

    /**
     * Clears the database
     */
    suspend fun clear() {
        userDao.deleteAllUsers()
    }
}
