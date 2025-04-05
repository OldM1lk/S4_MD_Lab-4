package com.example.lab_4.data.repository

import com.example.lab_4.data.local.Note
import com.example.lab_4.data.local.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun getAllNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
            return@withContext noteDao.getAllNotes()
        }
    }
    suspend fun insertNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    suspend fun deleteNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }
}