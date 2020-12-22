package com.example.mynotes.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.mynotes.*
import com.example.mynotes.databinding.FragmentAddNoteBinding
import com.example.mynotes.note.Note
import com.example.mynotes.note.NoteDatabase
import com.example.mynotes.noteslist.NoteViewModel
import com.example.mynotes.noteslist.NoteViewModelFactory
import kotlinx.coroutines.launch

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        binding.buttonSave.setOnClickListener{addNote()}

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).notesDatabaseDao

        val viewModelFactory = NoteViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun addNote()
    {
        val note = Note(title = binding.noteTitle.editableText.toString(), text = binding.noteText.editableText.toString())
        viewModel.viewModelScope.launch {
            viewModel.insert(note)
        }


        val navController = this.findNavController()
        navController.navigateUp()

        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()


    }
}