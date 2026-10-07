package com.bersyte.taskflow.di

import android.content.Context
import androidx.room.Room
import com.bersyte.taskflow.core.db.AppDatabase
import com.bersyte.taskflow.feature.notifications.data.repositories.INotificationRepository
import com.bersyte.taskflow.feature.notifications.data.repositories.NotificationRepository
import com.bersyte.taskflow.feature.notifications.service.INotificationService
import com.bersyte.taskflow.feature.notifications.service.TaskFlowNotificationService
import com.bersyte.taskflow.feature.profile.data.repositories.IProfileRepository
import com.bersyte.taskflow.feature.profile.data.repositories.ProfileRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.subtask.ISubtaskRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.subtask.SubtaskRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.taskLink.ITaskLinkRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.taskLink.TaskLinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    ).build()


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
