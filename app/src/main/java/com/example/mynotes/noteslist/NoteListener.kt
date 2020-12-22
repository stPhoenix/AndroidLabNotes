package com.example.mynotes.noteslist

import com.example.mynotes.note.Note

class NoteListener(val clickListener: (note: Note) -> Unit) {
    fun onClick(note: Note) = clickListener(note)
}