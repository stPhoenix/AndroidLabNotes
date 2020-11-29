package com.example.mynotes

import androidx.room.*

@Dao
interface NotesDatabaseDao {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * FROM notes_table WHERE noteId = :key")
    fun get(key: Long): Note?

    @Query("SELECT * FROM notes_table")
    fun get_all():List<Note>

    @Delete
    fun delete(key: Long)


}