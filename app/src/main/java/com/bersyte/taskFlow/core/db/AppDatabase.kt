package com.bersyte.taskFlow.core.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bersyte.taskFlow.feature.notifications.data.datasource.NotificationDao
import com.bersyte.taskFlow.feature.notifications.data.models.AppNotification
import com.bersyte.taskFlow.feature.profile.data.datasource.ProfileDao
import com.bersyte.taskFlow.feature.profile.data.models.AppUser
import com.bersyte.taskFlow.feature.tasks.data.datasource.SubtaskDao
import com.bersyte.taskFlow.feature.tasks.data.datasource.TaskDao
import com.bersyte.taskFlow.feature.tasks.data.datasource.TaskLinkDao
import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import com.bersyte.taskFlow.feature.tasks.data.models.TaskLink
import com.bersyte.taskFlow.utils.Converters


@Database(
    entities = [
        Task::class, TaskLink::class,
        Subtask::class, AppUser::class,
        AppNotification::class
    ],
    version = 5, exportSchema = true,
    autoMigrations = [
       // AutoMigration (from = 5, to = 6)
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao():SubtaskDao
    abstract fun taskLinkDao(): TaskLinkDao
    abstract fun profileDao(): ProfileDao
    abstract fun notificationDao(): NotificationDao
}
