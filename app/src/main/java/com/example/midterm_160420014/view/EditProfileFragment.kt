package com.example.midterm_160420014.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentEditProfileBinding
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.viewModel.UserViewModel


class EditProfileFragment : Fragment(),EditProfileListener {
    private lateinit var userVM: UserViewModel
    private lateinit var dataBinding:FragmentEditProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        userVM.getUser(sharedPref.getString("uuid","")!!.toInt())
        dataBinding.editProfileListener=this
        observe()
    }
    private fun observe(){
        userVM.userData.observe(viewLifecycleOwner, Observer {
            dataBinding.user=it
        })
    }
    private fun checkStatus(){
        userVM.updateStatus.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
            else{
                Toast.makeText(context, "Change Profile Failed!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditProfileBinding>(inflater,R.layout.fragment_edit_profile, container, false)
        return dataBinding.root
    }

    override fun onEditProfileClick(v: View,user:Users) {
        userVM.updateProfile(user.uuid,user.name!!,user.email!!,user.password!!,user.alamat!!)
        checkStatus()
    }
}