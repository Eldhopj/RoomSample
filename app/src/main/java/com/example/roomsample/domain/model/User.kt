package com.example.roomsample.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    @ColumnInfo(defaultValue = 0.toString())
    val age: Int? = null,
    @PrimaryKey(autoGenerate = true) // If unique ID comes from Backend, make it as false
    val id: Int = 0
): Parcelable
