package com.bersyte.taskmanagement.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bersyte.taskmanagement.core.data.AppDatabase
import com.bersyte.taskmanagement.feature.tasks.data.repositories.subtask.SubtaskRepository
import com.bersyte.taskmanagement.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskmanagement.feature.tasks.data.repositories.taskLink.TaskLinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    private val migration2To3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE tasks ADD COLUMN percentageCompleted FLOAT NOT NULL DEFAULT 0")
        }
    }


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "app_database"
    ).addMigrations(migration2To3).build()


    @Provides
    @Singleton
    fun provideTaskRepository(
        db: AppDatabase
    ): TaskRepository = TaskRepository(db)


    @Provides
    @Singleton
    fun provideSubtaskRepository(
        db: AppDatabase
    ): SubtaskRepository = SubtaskRepository(db)


    @Provides
    @Singleton
    fun provideTaskLinkRepository(
        db: AppDatabase
    ): TaskLinkRepository = TaskLinkRepository(db)

}
