package com.example.midterm_160420014.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.R
import com.example.midterm_160420014.viewModel.ListHistoryViewModel
import com.example.midterm_160420014.viewModel.ListMenuViewModel

class HistoryFragment : Fragment() {
    private lateinit var historyVM:ListHistoryViewModel
    private lateinit var menuVM:ListMenuViewModel
    private val historyAdapter=HistoryListAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        historyVM = ViewModelProvider(this)[ListHistoryViewModel::class.java]
        menuVM = ViewModelProvider(this)[ListMenuViewModel::class.java]
        sharedPref.getString("uuid","")?.let {
            menuVM.refreshAll()
            historyVM.refreshData(it.toInt())
        }

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=historyAdapter
        observe()
    }
    private fun observe(){
        historyVM.historyList.observe(viewLifecycleOwner, Observer {
            menuVM.menuList.observe(viewLifecycleOwner, Observer {menu->
                historyAdapter.updatehistoryList(it,menu)
            })
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
}