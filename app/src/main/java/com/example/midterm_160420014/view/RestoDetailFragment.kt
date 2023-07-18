package com.example.midterm_160420014.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.RequestQueue
import com.example.midterm_160420014.R
import com.example.midterm_160420014.model.Menus
import com.example.midterm_160420014.model.Promos
import com.example.midterm_160420014.model.Restaurants
import com.example.midterm_160420014.model.Reviews
import com.example.midterm_160420014.util.buildDB
import com.example.midterm_160420014.viewModel.RestoDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class RestoDetailFragment : Fragment(), BuyListener, PromoListener, ReviewListener {

    private lateinit var restoVM:RestoDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resto_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = RestoDetailFragmentArgs.fromBundle(requireArguments()).id
        restoVM = ViewModelProvider(this)[RestoDetailViewModel::class.java]
        restoVM.refresh(id)

        observe()
        view.findViewById<Button>(R.id.btnPromoDetail).setOnClickListener {
            Navigation.findNavController(it).navigate(RestoDetailFragmentDirections.actionRestoDetailFragmentToPromoFragment(id))
        }
        view.findViewById<Button>(R.id.btnBuy).setOnClickListener {
            Navigation.findNavController(it).navigate(RestoDetailFragmentDirections.actionRestoDetailFragmentToMenuFragment(id))
        }
        view.findViewById<Button>(R.id.btnReview).setOnClickListener {
            Navigation.findNavController(it).navigate(RestoDetailFragmentDirections.actionRestoDetailFragmentToReviewFragment(id))
        }
    }

    fun observe(){
        restoVM.menuList.observe(viewLifecycleOwner, Observer {
            restoVM.restoList.observe(viewLifecycleOwner,Observer{
                resto->
                view?.findViewById<TextView>(R.id.txtNamaDetail)?.text = it.name
                view?.findViewById<TextView>(R.id.txtPriceDetail)?.text = "Rp" + it.price.toString()
                view?.findViewById<TextView>(R.id.txtResto)?.text = resto.name
                Picasso.get().load(it.link).into(view?.findViewById<ImageView>(R.id.imgRestoDetail))
            })

        })
    }

    override fun onBuyDetailClick(v: View, menu: Menus, restaurant: Restaurants) {
        TODO("Not yet implemented")
    }

    override fun onPromoDetailClick(v: View, menu: Menus, promo: Promos) {
        TODO("Not yet implemented")
    }

    override fun onReviewDetailClick(v: View, menu: Menus, review: Reviews) {
        TODO("Not yet implemented")
    }


}