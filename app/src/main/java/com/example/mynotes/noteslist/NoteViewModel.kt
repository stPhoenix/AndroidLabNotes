package com.example.mynotes.noteslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mynotes.note.Note
import com.example.mynotes.note.NotesDatabaseDao


class NoteViewModel(private val database: NotesDatabaseDao, application: Application): AndroidViewModel(application) {
    val notes = database.get_all()

    suspend fun insert(note: Note)
    {
        database.insert(note)
    }

    suspend fun delete(note: Note)
    {
        database.delete(note)
    }

    suspend fun update(note: Note)
    {
        database.update(note)
    }

}