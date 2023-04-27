package com.example.roomsample.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.roomsample.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /** Use upsert only @Update(onConflict = OnConflictStrategy.REPLACE)
     *  If not need to replace if the value is already there use @Insert
    **/
    @Upsert
    suspend fun upsertUser(user: User)

    /**
        when using @Update it will skip since the value is not there
        instead if we use @Upsert if the primary key is not there new it will insert as a new value
    */
    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): Flow<List<User>>
}
