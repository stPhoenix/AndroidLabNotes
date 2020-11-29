package com.example.mynotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId:Long = 0L,
    @ColumnInfo(name = "noteDateMilli")
    var noteDateMilli:Long = System.currentTimeMillis(),
    @ColumnInfo(name = "title")
    var title:String = "",
    @ColumnInfo(name = "text")
    var text:String = ""

)
