package com.example.roomsample.data.local.typeconverters

import androidx.room.TypeConverter
import java.util.Date

/**
 * Room field type converter from Date object to Long & vice-versa
 */
object DateConverter {

    @JvmStatic
    @TypeConverter
    fun fromLong(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @JvmStatic
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
