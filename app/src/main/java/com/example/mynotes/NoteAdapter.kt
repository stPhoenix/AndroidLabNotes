package com.example.mynotes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mynotes.databinding.TextViewItemBinding
import java.text.SimpleDateFormat

class NoteAdapter(val clickListener: NoteListener, val textChangeListener: NoteListener): ListAdapter<Note, ViewHolder>(NoteDiffCallback())  {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val res = holder.itemView.context.resources

        holder.note_title.text = item.title.toString()
        holder.note_date.text = convertLongToDateString(item.noteDateMilli)
        holder.note_text.text = item.text
        holder.binding.note = item
        holder.binding.clickListener = clickListener

        holder.note_title.setOnFocusChangeListener{view, hasFocus ->
            if (!hasFocus && !item.title.equals(holder.note_title.editableText.toString()))
            {
                val  updatedNote = Note(item.noteId, item.noteDateMilli, holder.note_title.editableText.toString(), item.text)
                textChangeListener.onClick(updatedNote)
            }
        }

        holder.note_text.setOnFocusChangeListener{view, hasFocus ->
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