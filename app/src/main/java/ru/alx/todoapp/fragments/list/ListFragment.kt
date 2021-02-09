package ru.alx.todoapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.alx.todoapp.R
import ru.alx.todoapp.data.viewmodel.ToDoViewModel
import ru.alx.todoapp.fragments.SharedViewModel


class ListFragment : Fragment() {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var listLayout: ConstraintLayout
    private lateinit var recyclerView: RecyclerView

    private lateinit var no_data_imageView: ImageView
    private lateinit var no_data_textView: TextView

    private val adapter: ListAdapter by lazy { ListAdapter() }
//    private var adapter: ListAdapter? = null

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        floatingActionButton = view.findViewById(R.id.floatingActionButton) as FloatingActionButton
        listLayout = view.findViewById(R.id.listLayout) as ConstraintLayout
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView

        no_data_imageView = view.findViewById(R.id.no_data_imageView)
        no_data_textView = view.findViewById(R.id.no_data_textView)

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

//        listLayout.setOnClickListener {
//            findNavController().navigate(                                 R.id.action_listFragment_to_updateFragment)
//        }

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })

        mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })

        // set menu
        setHasOptionsMenu(true)

        return view
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            no_data_imageView.visibility = View.VISIBLE
            no_data_textView.visibility = View.VISIBLE
        } else {
            no_data_imageView.visibility = View.INVISIBLE
            no_data_textView.visibility = View.INVISIBLE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete_all) {
            confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(requireContext(), "Successfully Removed Everything", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_,_ ->}
        builder.setTitle("Delete Everything ?")
        builder.setMessage("Уверены что хотите удалить все ?")
        builder.create().show()

    }
}