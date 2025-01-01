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
    fun toTimestamp(date: LocalDateTime?): String {
        return date?.toString() ?: AppHelper.getCurrentDate().toString()
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String {
        return time.toString()
    }

    @TypeConverter
    fun toLocalTime(time: String?): LocalTime {
        return time?.let { LocalTime.parse(it) } ?: AppHelper.getCurrentDate().time
    }
}
