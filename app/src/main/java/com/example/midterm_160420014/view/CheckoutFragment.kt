package com.example.midterm_160420014.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentCheckoutBinding
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.viewModel.ListHistoryViewModel
import com.example.midterm_160420014.viewModel.RestoDetailViewModel
import com.example.midterm_160420014.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class CheckoutFragment : Fragment(),CheckoutListener {
    lateinit var menuVM: RestoDetailViewModel
    lateinit var userVM:UserViewModel
    lateinit var historyVM:ListHistoryViewModel
    lateinit var dataBinding:FragmentCheckoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = DataBindingUtil.inflate<FragmentCheckoutBinding>(inflater,R.layout.fragment_checkout, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuVM = ViewModelProvider(this)[RestoDetailViewModel::class.java]
        userVM  =ViewModelProvider(this)[UserViewModel::class.java]
        historyVM = ViewModelProvider(this)[ListHistoryViewModel::class.java]
        observe()
    }

    fun observe(){
        menuVM.menuList.observe(viewLifecycleOwner, Observer {
            dataBinding.menu = it
            dataBinding.user = userVM.userData.value
        })
    }

    override fun onClickCheckout(v: View,user: Users) {
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val id = sharedPref.getString("uuid","")!!.toInt()
        val qty = view?.findViewById<TextInputEditText>(R.id.editTextQty)
        val subtotal = view?.findViewById<TextView>(R.id.txtCheckoutSubtotal)?.text.toString().toInt()
        historyVM.checkoutOrder(id,menuVM.menuList.value!!.uuid,qty?.text.toString().toInt(),user.alamat!!,subtotal)
    }

    override fun onQtyTextChange(s: CharSequence, start: Int, before: Int, count: Int) {
        var qty = s.toString().toInt()
        var subtotal = menuVM.menuList.value!!.price*qty
        view?.findViewById<TextView>(R.id.txtCheckoutSubtotal)?.text = subtotal.toString()
    }
}