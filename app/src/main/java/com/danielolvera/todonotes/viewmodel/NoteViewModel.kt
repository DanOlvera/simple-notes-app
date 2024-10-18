package com.danielolvera.todonotes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.danielolvera.todonotes.intent.NoteIntent
import com.danielolvera.todonotes.model.local.Note
import com.danielolvera.todonotes.model.local.NoteDatabase
import com.danielolvera.todonotes.state.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application){

    private val noteDao = NoteDatabase.getDataBase(application).noteDao()

    // We're using state flow to hold UI state
    private val _state = MutableStateFlow<NoteState>(NoteState.Loading)
    val state: StateFlow<NoteState> = _state.asStateFlow()

    init {
        handleIntent(NoteIntent.LoadNotes)
    }

    fun handleIntent(intent: NoteIntent) {
        when(intent) {
            is NoteIntent.LoadNotes -> loadNotes()
            is NoteIntent.AddNote -> addNote(intent.title, intent.description)
            is NoteIntent.UpdateNote -> updateNote(intent.note)
            is NoteIntent.DeleteNote -> deleteNote(intent.note)
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteDao.getAllNotes().collect { notes ->
                _state.value = NoteState.Success(notes)
            }
        }
    }

    private fun addNote(title: String, description: String) {
        viewModelScope.launch {
            val note = Note(title = title, description = description)
            noteDao.insertNote(note)
            loadNotes()
        }
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.update(note)
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }
}