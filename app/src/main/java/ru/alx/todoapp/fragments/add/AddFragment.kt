package ru.alx.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.alx.todoapp.R
import ru.alx.todoapp.data.models.Priority
import ru.alx.todoapp.data.models.ToDoData
import ru.alx.todoapp.data.viewmodel.ToDoViewModel
import ru.alx.todoapp.fragments.SharedViewModel


class AddFragment : Fragment() {

    private lateinit var title_et: EditText
    private lateinit var priorities_spinner: Spinner
    private lateinit var description_et: EditText

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // Set Menu
        setHasOptionsMenu(true)

        title_et = view.findViewById(R.id.title_et)
        priorities_spinner = view.findViewById(R.id.priorities_spinner)
        description_et = view.findViewById(R.id.description_et)

        priorities_spinner.onItemSelectedListener = mSharedViewModel.listener


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            // insert data to database
            val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )

            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Succesfully added!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun verifyDataFromUser(title: String, description: String): Boolean {
//        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
//    }
//
//    private fun parsePriority(priority: String): Priority {
//        return when (priority) {
//            "High Priority" -> {Priority.HIGH}
//            "Medium Priority" -> {Priority.MEDIUM}
//            "Low Priority" -> {Priority.LOW}
//            else -> Priority.LOW
//        }
//    }
}