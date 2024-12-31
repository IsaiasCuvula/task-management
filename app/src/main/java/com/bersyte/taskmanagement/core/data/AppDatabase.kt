package com.bersyte.taskmanagement.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bersyte.taskmanagement.feature.tasks.data.datasource.SubtaskDao
import com.bersyte.taskmanagement.feature.tasks.data.datasource.TaskDao
import com.bersyte.taskmanagement.feature.tasks.data.datasource.TaskLinkDao
import com.bersyte.taskmanagement.feature.tasks.data.models.Subtask
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskLink
import com.bersyte.taskmanagement.utils.Converters


@Database(
    entities = [Task::class, TaskLink::class, Subtask::class],
    version = 1, exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao():SubtaskDao
    abstract fun taskLinkDao(): TaskLinkDao
}
