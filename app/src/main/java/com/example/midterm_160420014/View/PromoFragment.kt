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
import com.example.midterm_160420014.ViewModel.ListPromoViewModel

class PromoFragment : Fragment() {
    private lateinit var promoVM:ListPromoViewModel
    private val promoAdapter = PromoListAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = PromoFragmentArgs.fromBundle(requireArguments()).id
        promoVM = ViewModelProvider(this)[ListPromoViewModel::class.java]
        promoVM.refreshData(id)

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=promoAdapter
        observe()
    }

    fun observe(){
        promoVM.promoList.observe(viewLifecycleOwner, Observer {
            promoAdapter.updatepromoList(it)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }
}