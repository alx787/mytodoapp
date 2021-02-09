package ru.alx.todoapp.fragments.list

import android.os.Bundle
import android.view.*
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


class ListFragment : Fragment() {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var listLayout: ConstraintLayout
    private lateinit var recyclerView: RecyclerView

    private val adapter: ListAdapter by lazy { ListAdapter() }
//    private var adapter: ListAdapter? = null

    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        floatingActionButton = view.findViewById(R.id.floatingActionButton) as FloatingActionButton
        listLayout = view.findViewById(R.id.listLayout) as ConstraintLayout
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView


        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

//        listLayout.setOnClickListener {
//            findNavController().navigate(                                 R.id.action_listFragment_to_updateFragment)
//        }

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })

        // set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}