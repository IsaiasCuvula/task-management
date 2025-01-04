package com.bersyte.taskmanagement.feature.profile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskmanagement.utils.AppHelper
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "user_info")
data class AppUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var username: String = "Your username",
    val createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
