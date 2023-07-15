package com.example.midterm_160420014.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.midterm_160420014.R
import com.example.midterm_160420014.ViewModel.ListPromoViewModel
import com.example.midterm_160420014.ViewModel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var userVM:UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        val sharedPref = requireActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
//        userVM.refresh(sharedPref.getString("id","")!!)
        observe()
    }
    fun observe(){
        userVM.userData.observe(viewLifecycleOwner, Observer {
            Log.d("User: ",it.toString())
            view?.findViewById<TextView>(R.id.txtName)?.text=it.name
            view?.findViewById<TextView>(R.id.txtEmail)?.text=it.email
            view?.findViewById<TextView>(R.id.txtPassword)?.text=it.password

        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}