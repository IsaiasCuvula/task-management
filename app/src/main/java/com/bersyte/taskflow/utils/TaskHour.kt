package com.bersyte.taskflow.utils

import kotlinx.datetime.LocalTime

enum class TaskHour(val hour: Int) {
    H0(0),
    H1(1),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6),
    H7(7),
    H8(8),
    H9(9),
    H10(10),
    H11(11),
    H12(12),
    H13(13),
    H14(14),
    H15(15),
    H16(16),
    H17(17),
    H18(18),
    H19(19),
    H20(20),
    H21(21),
    H22(22),
    H23(23);

    fun toLocalTime(): LocalTime {
        return LocalTime(hour, 0)
    }

    companion object {
        fun currentHour(): TaskHour {
            val currentHour = AppHelper.getCurrentDate().hour
            return entries.first { it.hour == currentHour }
        }
    }
}
