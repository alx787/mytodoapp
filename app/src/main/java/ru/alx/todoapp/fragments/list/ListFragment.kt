package ru.alx.todoapp.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.alx.todoapp.R


class ListFragment : Fragment() {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var listLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false)
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        listLayout = view.findViewById(R.id.listLayout)


        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        // set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}