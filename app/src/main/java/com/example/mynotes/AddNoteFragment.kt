package com.example.mynotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mynotes.databinding.FragmentAddNoteBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentAddNoteBinding
    lateinit var viewModel: NoteViewModel
    var noteDate : Long = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddNoteBinding>(inflater, R.layout.fragment_add_note, container, false)

        binding.buttonSave.setOnClickListener{addNote()}

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).notesDatabaseDao

        val viewModelFactory = NoteViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        binding.setLifecycleOwner(this)

        return binding.root
    }

    fun addNote()
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

    @SuppressLint("SimpleDateFormat")
    fun convertLongToDateString(systemTime: Long): String {
        return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(systemTime).toString()

}