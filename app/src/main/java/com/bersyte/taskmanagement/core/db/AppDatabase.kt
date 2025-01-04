package com.bersyte.taskmanagement.core.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bersyte.taskmanagement.feature.profile.data.datasource.ProfileDao
import com.bersyte.taskmanagement.feature.profile.data.models.AppUser
import com.bersyte.taskmanagement.feature.tasks.data.datasource.SubtaskDao
import com.bersyte.taskmanagement.feature.tasks.data.datasource.TaskDao
import com.bersyte.taskmanagement.feature.tasks.data.datasource.TaskLinkDao
import com.bersyte.taskmanagement.feature.tasks.data.models.Subtask
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskLink
import com.bersyte.taskmanagement.utils.Converters


@Database(
    entities = [
        Task::class, TaskLink::class,
        Subtask::class, AppUser::class
    ],
    version = 4, exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao():SubtaskDao
    abstract fun taskLinkDao(): TaskLinkDao
    abstract fun profileDao(): ProfileDao
}
