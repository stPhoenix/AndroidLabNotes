package com.example.mynotes

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
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
import com.example.mynotes.databinding.FragmentNotesListBinding
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NotesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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

        binding.setLifecycleOwner(this)
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