package com.example.lab_4.ui.screens.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_4.data.local.Note
import com.example.lab_4.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _currentNote = MutableStateFlow<Note?>(null)
    val currentNote: StateFlow<Note?> = _currentNote

    fun updateTitleState(title: String) {
        _currentNote.update {
            it?.copy(
                title = title
            )
        }
    }

    fun updateContentState(content: String) {
        _currentNote.update {
            it?.copy(
                content = content
            )
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            _currentNote.value = repository.getNoteById(id)
        }
    }
}