package com.example.mynotes

import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.TextViewItemBinding

class ViewHolder(val binding: TextViewItemBinding): RecyclerView.ViewHolder(binding.root){
    val note_title:TextView = binding.noteTitle
    val note_date:TextView = binding.noteDate
    val note_text:TextView = binding.noteText
}
