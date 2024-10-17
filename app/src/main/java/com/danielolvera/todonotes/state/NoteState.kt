package com.danielolvera.todonotes.state

import com.danielolvera.todonotes.model.local.Note

sealed class NoteState {
    data object Loading: NoteState()
    data class Success(val notes: List<Note>): NoteState()
    data class Error(val message: String): NoteState()
}