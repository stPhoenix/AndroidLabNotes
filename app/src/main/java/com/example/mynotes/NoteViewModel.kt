package com.example.mynotes

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class NoteViewModel(private val database: NotesDatabaseDao, application: Application): AndroidViewModel(application) {
    val notes = database.get_all()

    suspend fun insert(note:Note)
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