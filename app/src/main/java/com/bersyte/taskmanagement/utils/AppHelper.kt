package com.bersyte.taskmanagement.utils

import android.content.Context
import android.widget.Toast
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object AppHelper {

    fun showToast(context: Context, msg: String){
       return Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    fun getCurrentDate(): LocalDateTime{
        val now = Clock.System.now()
        return now.toLocalDateTime(
            TimeZone.currentSystemDefault()
        )
    }

    fun getDaysOfTheWeek(): List<LocalDate> {
        val today = getCurrentDate()
        val todayWeekDay = today.dayOfWeek.isoDayNumber

        // Calculate the start of the week (Monday)
        val startOfWeek = today.date.minus(
            (todayWeekDay - 1).toLong(), DateTimeUnit.DAY
        )

        // Generate the list of dates for the week
        return (0 until 7).map { offset ->
            startOfWeek.plus(DatePeriod(days = offset))
        }
    }

    fun longToDate(value: Long?): LocalDate {
        return value?.let {
           Instant.fromEpochMilliseconds(it)
               .toLocalDateTime(
                   TimeZone.currentSystemDefault()
               ).date
        } ?: getCurrentDate().date
    }

    fun timeStateToLocalTime(hour: Int, minute: Int): LocalTime{
      return LocalTime(hour, minute)
    }
}
