package com.example.lab_4.di

import android.app.Application
import androidx.room.Room
import com.example.lab_4.data.local.NoteDao
import com.example.lab_4.data.local.NoteDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun provideDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }
}