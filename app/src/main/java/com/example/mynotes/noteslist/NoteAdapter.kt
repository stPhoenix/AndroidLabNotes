package com.example.mynotes.noteslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mynotes.databinding.TextViewItemBinding
import com.example.mynotes.note.Note
import java.text.SimpleDateFormat

class NoteAdapter(private val clickListener: NoteListener, private val textChangeListener: NoteListener): ListAdapter<Note, ViewHolder>(NoteDiffCallback())  {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.note_title.text = item.title
        holder.note_date.text = convertLongToDateString(item.noteDateMilli)
        holder.note_text.text = item.text
        holder.binding.note = item
        holder.binding.clickListener = clickListener

        holder.note_title.setOnFocusChangeListener{ _, hasFocus ->
            if (!hasFocus && !item.title.equals(holder.note_title.editableText.toString()))
            {
                val  updatedNote = Note(item.noteId, item.noteDateMilli, holder.note_title.editableText.toString(), item.text)
                textChangeListener.onClick(updatedNote)
            }
        }

        holder.note_text.setOnFocusChangeListener{ _, hasFocus ->
            if (!hasFocus && !item.text.equals(holder.note_text.editableText.toString()))
            {
                val  updatedNote = Note(item.noteId, item.noteDateMilli, item.title, holder.note_text.editableText.toString())
                textChangeListener.onClick(updatedNote)
            }
        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TextViewItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToDateString(systemTime: Long): String {
        return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(systemTime).toString()
    }


}