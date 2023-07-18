package com.example.midterm_160420014.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.R
import com.example.midterm_160420014.model.Menus
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.viewModel.ListRestoViewModel

class HomeFragment : Fragment(){
private lateinit var restoVM:ListRestoViewModel
    private val restaurantListAdapter=RestaurantListAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoVM = ViewModelProvider(this).get(ListRestoViewModel::class.java)
        restoVM.refreshData()
        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager=LinearLayoutManager(context)
        recView.adapter=restaurantListAdapter
        observe()
    }
    fun observe(){
        restoVM.menuList.observe(viewLifecycleOwner, Observer {
            restaurantListAdapter.updateRestoList(it,requireContext())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}