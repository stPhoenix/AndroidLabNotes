package com.example.mynotes.noteslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.mynotes.*
import com.example.mynotes.databinding.FragmentNotesListBinding
import com.example.mynotes.note.NoteDatabase
import kotlinx.coroutines.launch


class NotesListFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNotesListBinding>(inflater, R.layout.fragment_notes_list, container, false)

        binding.button.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_notesListFragment_to_addNoteFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).notesDatabaseDao

        val viewModelFactory = NoteViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        binding.lifecycleOwner = this
        binding.notesViewModel = viewModel

        val adapter = NoteAdapter(NoteListener { note ->
            viewModel.viewModelScope.launch { viewModel.delete(note) }
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show() },
                                  NoteListener { note ->
                                      viewModel.viewModelScope.launch { viewModel.update(note) }
                                      Log.d("LIST FRAGMENT", "Updated")
                                      Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()})
        binding.notesList.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return  binding.root
    }

}