package com.example.keepnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesAdapter {

  lateinit var mAdpater:NotesAdpater
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycle=findViewById<RecyclerView>(R.id.recyclerview)
        recycle.layoutManager=LinearLayoutManager(this)
        mAdpater=NotesAdpater(this,this)
        recycle.adapter=mAdpater

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer {list ->
            list?.let {
                mAdpater.update(it)
            }
        })

    }

    override fun OnItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun save(view: View) {
        val input = findViewById<EditText>(R.id.input)
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "${noteText} Inserted", Toast.LENGTH_LONG).show()
        }
    }
}