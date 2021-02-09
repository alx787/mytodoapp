package ru.alx.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.alx.todoapp.R
import ru.alx.todoapp.data.models.Priority
import ru.alx.todoapp.data.models.ToDoData
import ru.alx.todoapp.data.viewmodel.ToDoViewModel
import ru.alx.todoapp.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private lateinit var current_title_et: EditText
    private lateinit var current_priorities_spinner: Spinner
    private lateinit var current_description_et: EditText


    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        current_title_et = view.findViewById(R.id.current_title_et) as EditText
        current_priorities_spinner = view.findViewById(R.id.current_priorities_spinner) as Spinner
        current_description_et = view.findViewById(R.id.current_description_et) as EditText


        current_title_et.setText(args.currentitem.title)
        current_description_et.setText(args.currentitem.description)
        current_priorities_spinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentitem.priority))



        // Set Menu
        setHasOptionsMenu(true)



        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = current_title_et.editableText.toString()
        val description = current_description_et.text.toString()
        val getPriority = current_priorities_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val updateItem = ToDoData(args.currentitem.id, title, mSharedViewModel.parsePriority(getPriority), description)
            mToDoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show();
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show();

        }
    }
}