package com.example.lab_4.di

import com.example.lab_4.data.local.NoteDao
import com.example.lab_4.data.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }
}