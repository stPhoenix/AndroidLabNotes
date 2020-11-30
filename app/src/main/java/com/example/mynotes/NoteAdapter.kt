package com.example.mynotes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.NoteViewModel
import java.text.SimpleDateFormat

class NoteAdapter: RecyclerView.Adapter<TextItemViewHolder>()  {
    var data =  listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources

        holder.note_title.text = item.title.toString()
        holder.note_data.text = convertLongToDateString(item.noteDateMilli)
        holder.note_text.text = item.text


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_view_item, parent, false) as TextView

        return TextItemViewHolder(view)

    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToDateString(systemTime: Long): String {
        return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(systemTime).toString()
    }
}