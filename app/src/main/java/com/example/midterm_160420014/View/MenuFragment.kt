package com.example.midterm_160420014.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.R
import com.example.midterm_160420014.ViewModel.ListMenuViewModel
import com.example.midterm_160420014.ViewModel.ListRestoViewModel

class MenuFragment : Fragment() {
    private lateinit var menuVM: ListMenuViewModel
    private val menuListAdapter=MenuListAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = MenuFragmentArgs.fromBundle(requireArguments()).id
        menuVM = ViewModelProvider(this).get(ListMenuViewModel::class.java)
        menuVM.refreshData(id)

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=menuListAdapter
        observe()
    }
    fun observe(){
        menuVM.menuList.observe(viewLifecycleOwner, Observer {
            menuListAdapter.updatemenuList(it)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
}