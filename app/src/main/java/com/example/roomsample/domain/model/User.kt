package com.example.roomsample.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
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
    /** use @Embedded if all the values inside the object are primary values,
     *  if there is any object inside the current object check the same whether those are primary
     *  if anything not in primary use @TypeConverters only on that variable
     *          And the objects in the same package have to use @Embedded again
     *          eg: Since Date uses @TypeConverters, Address object which is on the same pkg hav to use @Embedded*/
    @Embedded
    val contactDetails: ContactDetails,
    @PrimaryKey(autoGenerate = true) // If unique ID comes from Backend, make it as false
    val id: Int = 0
): Parcelable
