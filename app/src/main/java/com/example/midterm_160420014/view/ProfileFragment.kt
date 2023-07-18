package com.example.midterm_160420014.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentProfileBinding
import com.example.midterm_160420014.viewModel.UserViewModel

class ProfileFragment : Fragment(), ProfileOnClickListener {
    private lateinit var userVM:UserViewModel
    private lateinit var dataBinding:FragmentProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        userVM.getUser(sharedPref.getString("uuid","")!!.toInt())
        dataBinding.profileListener=this
        observe()
        val btnlogout=view.findViewById<Button>(R.id.buttonLogout)
        btnlogout.setOnClickListener{
            val editor:SharedPreferences.Editor=sharedPref.edit()
            editor.clear()
            editor.apply()

            userVM.clearData()
            findNavController().popBackStack(findNavController().graph.startDestinationId,true)
            val navController = Navigation.findNavController(requireActivity(),R.id.fragment_host)
            navController.popBackStack(navController.graph.startDestinationId,false)
            navController.navigate(R.id.loginFragment)

            activity?.recreate()

            val backstack = navController.backQueue
            for (entry in backstack){
                Log.d("BACKSTACK: ",entry.destination.displayName)
            }
            for(entri in findNavController().backQueue){
                Log.d("BACKSTACK PREV: ",entri.destination.displayName)
            }

        }
    }
    private fun observe(){
        userVM.userData.observe(viewLifecycleOwner, Observer {
            dataBinding.user=it
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onProfileClick(v: View) {
        val action=ProfileFragmentDirections.actionItemProfileToEditProfileFragment()
        Navigation.findNavController(v).navigate(action)
    }
}