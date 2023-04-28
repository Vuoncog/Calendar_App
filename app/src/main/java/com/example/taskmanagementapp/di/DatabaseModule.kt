package com.example.taskmanagementapp.di

import android.content.Context
import androidx.room.Room
import com.example.taskmanagementapp.constant.DatabaseConstant.DATABASE_NAME
import com.example.taskmanagementapp.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, NoteDatabase::class.java, DATABASE_NAME
    )
        .build()

    @Singleton
    @Provides
    fun providesDao(database: NoteDatabase) = database.noteDAO()
}