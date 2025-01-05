package com.bersyte.taskFlow.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bersyte.taskFlow.core.db.AppDatabase
import com.bersyte.taskFlow.feature.notifications.data.repositories.INotificationRepository
import com.bersyte.taskFlow.feature.notifications.data.repositories.NotificationRepository
import com.bersyte.taskFlow.feature.notifications.service.INotificationService
import com.bersyte.taskFlow.feature.notifications.service.TaskFlowNotificationService
import com.bersyte.taskFlow.feature.profile.data.repositories.IProfileRepository
import com.bersyte.taskFlow.feature.profile.data.repositories.ProfileRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.subtask.ISubtaskRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.subtask.SubtaskRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.taskLink.ITaskLinkRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.taskLink.TaskLinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val migration4To5 = object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                """
            CREATE TABLE IF NOT EXISTS notifications (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                taskId INTEGER NOT NULL,
                isSeen INTEGER NOT NULL DEFAULT 0,
                createdAt TEXT NOT NULL
            )
            """.trimIndent()
            )
        }
    }

    @Provides
    @Singleton
    fun provideNotificationService(
        @ApplicationContext context: Context
    ): INotificationService {
        return TaskFlowNotificationService(context)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "app_database"
    ).addMigrations(migration4To5).build()


    @Provides
    @Singleton
    fun provideTaskRepository(
        db: AppDatabase
    ): ITaskRepository = TaskRepository(db)


    @Provides
    @Singleton
    fun provideSubtaskRepository(
        db: AppDatabase
    ): ISubtaskRepository = SubtaskRepository(db)


    @Provides
    @Singleton
    fun provideTaskLinkRepository(
        db: AppDatabase
    ): ITaskLinkRepository = TaskLinkRepository(db)

    @Provides
    @Singleton
    fun provideProfileRepository(
        db: AppDatabase
    ): IProfileRepository = ProfileRepository(db)


    @Provides
    @Singleton
    fun provideNotificationsRepository(
        db: AppDatabase
    ): INotificationRepository = NotificationRepository(db)

}
