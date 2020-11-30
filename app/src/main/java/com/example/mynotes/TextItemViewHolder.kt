package com.example.mynotes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextItemViewHolder(val itemView : View): RecyclerView.ViewHolder(itemView){
    val note_title:TextView = itemView.findViewById(R.id.note_title)
    val note_data:TextView = itemView.findViewById(R.id.note_data)
    val note_text:TextView = itemView.findViewById(R.id.note_text)
}
