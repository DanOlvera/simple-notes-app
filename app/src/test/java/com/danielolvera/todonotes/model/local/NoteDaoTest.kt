package com.danielolvera.todonotes.model.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.junit.After
import org.junit.Before
import org.junit.Test

class NoteDaoTest {

    private lateinit var database: NoteDatabase
    private lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        noteDao = database.noteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNote_insertsSuccessfully() = runTest {
        val note = Note(id = 1, title = "Test Note", description = "Test Description")
        noteDao.insertNote(note)

        val loadedNote = noteDao.getNoteById(1)
        loadedNote.shouldNotBeNull()
        "Test Note" shouldBeEqualTo loadedNote.title
    }

    @Test
    fun deleteNote_removesNote() = runTest {
        val note = Note(id = 1, title = "Test Note", description = "Test Description")
        noteDao.insertNote(note)
        noteDao.delete(note)

        val loadedNote = noteDao.getNoteById(1)
        loadedNote.shouldBeNull()
    }
}
