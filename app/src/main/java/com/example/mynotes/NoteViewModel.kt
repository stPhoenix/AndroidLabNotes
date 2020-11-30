package com.example.mynotes

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class NoteViewModel(val database: NotesDatabaseDao, application: Application): AndroidViewModel(application) {
    val notes = database.get_all()

    private suspend fun insert(note:Note)
    {
        database.insert(note)
    }


}