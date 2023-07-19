package com.example.midterm_160420014.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.RequestQueue
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentProfileBinding
import com.example.midterm_160420014.databinding.FragmentRestoDetailBinding
import com.example.midterm_160420014.model.*
import com.example.midterm_160420014.util.buildDB
import com.example.midterm_160420014.viewModel.RestoDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class RestoDetailFragment : Fragment(), BuyListener, ReviewListener {

    private lateinit var restoVM:RestoDetailViewModel
    private lateinit var dataBinding:FragmentRestoDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentRestoDetailBinding>(inflater,R.layout.fragment_resto_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = RestoDetailFragmentArgs.fromBundle(requireArguments()).id
        restoVM = ViewModelProvider(this)[RestoDetailViewModel::class.java]
        restoVM.refresh(id)
        dataBinding.buyListener=this
        dataBinding.reviewListener=this
        observe()
    }

    fun observe(){
        restoVM.menuList.observe(viewLifecycleOwner, Observer {
            restoVM.restoList.observe(viewLifecycleOwner,Observer{
                resto->
                dataBinding.detailMenu = it
                dataBinding.detailResto = resto
            })

        })
    }

    override fun onBuyDetailClick(v: View, menu: Menus, restaurant: Restaurants) {
        val action = RestoDetailFragmentDirections.actionRestoDetailFragmentToCheckoutFragment(menu.uuid,menu.restoId)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onReviewDetailClick(v: View, menu: Menus, restaurant: Restaurants) {
        val uuid = context?.getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
            ?.getString("uuid","")!!.toInt()
        val action = RestoDetailFragmentDirections.actionRestoDetailFragmentToReviewFragment(restaurant.uuid,menu.uuid)
        Navigation.findNavController(v).navigate(action)
    }


}