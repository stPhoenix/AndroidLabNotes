package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDatabaseDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM notes_table WHERE noteId = :key")
    suspend fun get(key: Long): Note?

    @Query("SELECT * FROM notes_table")
    fun get_all():LiveData<List<Note>>

    @Delete
    suspend fun delete(note: Note)


}