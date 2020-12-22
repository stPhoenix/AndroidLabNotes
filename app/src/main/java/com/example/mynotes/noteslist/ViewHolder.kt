package com.example.mynotes.noteslist

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.TextViewItemBinding

class ViewHolder(val binding: TextViewItemBinding): RecyclerView.ViewHolder(binding.root){
    val note_title:TextView = binding.noteTitle
    val note_date:TextView = binding.noteDate
    val note_text:TextView = binding.noteText
}
