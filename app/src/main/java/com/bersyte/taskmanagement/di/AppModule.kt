package com.bersyte.taskmanagement.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bersyte.taskmanagement.core.db.AppDatabase
import com.bersyte.taskmanagement.feature.profile.data.repositories.ProfileRepository
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


    private val migration3To4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS user_info (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    username TEXT NOT NULL DEFAULT 'Your username',
                    createdAt TEXT NOT NULL
                )
                """.trimIndent()
            )
        }
    }


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "app_database"
    ).addMigrations(migration3To4).build()


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

    @Provides
    @Singleton
    fun provideProfileRepository(
        db: AppDatabase
    ): ProfileRepository = ProfileRepository(db)

}
