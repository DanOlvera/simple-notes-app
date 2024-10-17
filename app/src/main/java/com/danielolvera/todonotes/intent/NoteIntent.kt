package com.danielolvera.todonotes.intent

import com.danielolvera.todonotes.model.local.Note

sealed class NoteIntent {
    data object LoadNotes: NoteIntent()
    data class AddNote(val title: String, val description: String): NoteIntent()
    data class UpdateNote(val note: Note): NoteIntent()
    data class DeleteNote(val note: Note): NoteIntent()
}