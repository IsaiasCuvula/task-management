package com.bersyte.taskflow.core.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bersyte.taskflow.feature.notifications.data.datasource.NotificationDao
import com.bersyte.taskflow.feature.notifications.data.models.AppNotification
import com.bersyte.taskflow.feature.profile.data.datasource.ProfileDao
import com.bersyte.taskflow.feature.profile.data.models.AppUser
import com.bersyte.taskflow.feature.tasks.data.datasource.SubtaskDao
import com.bersyte.taskflow.feature.tasks.data.datasource.TaskDao
import com.bersyte.taskflow.feature.tasks.data.datasource.TaskLinkDao
import com.bersyte.taskflow.feature.tasks.data.models.Subtask
import com.bersyte.taskflow.feature.tasks.data.models.Task
import com.bersyte.taskflow.feature.tasks.data.models.TaskLink
import com.bersyte.taskflow.utils.Converters


@Database(
    entities = [
        Task::class, TaskLink::class,
        Subtask::class, AppUser::class,
        AppNotification::class
    ],
    version = 1, exportSchema = true,
    autoMigrations = [
        // AutoMigration (from = 5, to = 6)
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao(): SubtaskDao
    abstract fun taskLinkDao(): TaskLinkDao
    abstract fun profileDao(): ProfileDao
    abstract fun notificationDao(): NotificationDao
}
