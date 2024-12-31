package com.bersyte.taskmanagement.utils

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class Converters {

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime {
        return value?.let { LocalDateTime.parse(it) } ?: AppHelper.getCurrentDate()
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String {
        return date?.toString() ?: AppHelper.getCurrentDate().toString()
    }


    @TypeConverter
    fun timeToLocalTime(hour: Int, minute: Int): LocalTime {
        return LocalTime(hour, minute)
    }
}
