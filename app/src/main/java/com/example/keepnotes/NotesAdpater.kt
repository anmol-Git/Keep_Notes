package com.example.keepnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdpater(private val context: Context,private val listener:INotesAdapter): RecyclerView.Adapter<NotesAdpater.NoteViewHolder>() {
    val  allNote=ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.notes)
        val deleteButton=itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.OnItemClicked(allNote[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNote[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNote.size
    }
    fun update(newList:List<Note>){
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun  OnItemClicked(note:Note)
}