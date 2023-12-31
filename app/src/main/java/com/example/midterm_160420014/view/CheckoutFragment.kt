package com.example.midterm_160420014.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentCheckoutBinding
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.util.CheckoutWorker
import com.example.midterm_160420014.util.NotificationHelper
import com.example.midterm_160420014.viewModel.ListHistoryViewModel
import com.example.midterm_160420014.viewModel.RestoDetailViewModel
import com.example.midterm_160420014.viewModel.UserViewModel
import java.util.concurrent.TimeUnit

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
        dataBinding = DataBindingUtil.inflate<FragmentCheckoutBinding>(inflater,R.layout.fragment_checkout, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuVM = ViewModelProvider(this)[RestoDetailViewModel::class.java]
        userVM  =ViewModelProvider(this)[UserViewModel::class.java]
        historyVM = ViewModelProvider(this)[ListHistoryViewModel::class.java]
        dataBinding.checkoutListener = this
        view.findViewById<Button>(R.id.btnCheckout).isEnabled = false

        val ids = ReviewFragmentArgs.fromBundle(requireArguments())
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val id = sharedPref.getString("uuid","")!!.toInt()
        menuVM.getMenu(ids.menuId)
        userVM.getUser(id)
        observe()
    }
    private fun observe(){
        menuVM.menuList.observe(viewLifecycleOwner, Observer {menu->
            userVM.userData.observe(viewLifecycleOwner, Observer {user->
                dataBinding.user=user
                dataBinding.menu=menu
            })
        })
        historyVM.subtotal.observe(viewLifecycleOwner, Observer {
            dataBinding.subtotal = it
        })
    }

    override fun onClickCheckout(v: View,user: Users) {
        if(user.saldo!! <historyVM.subtotal.value!!.toInt()){
            Toast.makeText(context,"Saldo tidak mencukupi!!",Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("MENU: ",menuVM.menuList.value!!.uuid.toString())
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val id = sharedPref.getString("uuid","")!!.toInt()
        historyVM.checkoutOrder(id,menuVM.menuList.value!!.uuid,historyVM.qty.value!!,user.alamat!!,historyVM.subtotal.value!!)

//        NotificationHelper(v.context).createNotification("Checkout create","A new checkout successfully created!!")
        val workReq = OneTimeWorkRequestBuilder<CheckoutWorker>().setInitialDelay(1, TimeUnit.SECONDS).setInputData(
            workDataOf("title" to "Checkout create", "message" to "A new checkout successfully created!!")
        ).build()
        WorkManager.getInstance(requireContext()).enqueue(workReq)
        findNavController().popBackStack()
        Toast.makeText(context,"Checkout Success!!",Toast.LENGTH_SHORT).show()
    }

    private fun setButtonEnabled(){
        view?.findViewById<Button>(R.id.btnCheckout)?.isEnabled = true
    }
    private fun setButtonDisabled(){
        view?.findViewById<Button>(R.id.btnCheckout)?.isEnabled = false
    }
    override fun onQtyTextChange(s: CharSequence, start: Int, before: Int, count: Int) {
        val qty = s.toString()
        if(qty!="" && qty.toInt()>0){
            historyVM.qty.postValue(qty.toInt())
            historyVM.subtotal.postValue(menuVM.menuList.value!!.price * qty.toInt())
            setButtonEnabled()
        }
        else{
            setButtonDisabled()
            historyVM.subtotal.postValue(0)
        }
    }
}