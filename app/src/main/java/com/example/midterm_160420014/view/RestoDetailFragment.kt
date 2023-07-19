package com.example.midterm_160420014.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentRestoDetailBinding
import com.example.midterm_160420014.model.*
import com.example.midterm_160420014.viewModel.RestoDetailViewModel


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
        val action = RestoDetailFragmentDirections.actionRestoDetailFragmentToReviewFragment(restaurant.uuid,menu.uuid)
        Navigation.findNavController(v).navigate(action)
    }


}