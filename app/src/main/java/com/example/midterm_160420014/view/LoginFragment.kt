package com.example.midterm_160420014.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.midterm_160420014.R
import com.example.midterm_160420014.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText



class LoginFragment : Fragment() {

    private lateinit var userVM:UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        val userlogged:SharedPreferences=requireActivity().getSharedPreferences("UserLogin",Context.MODE_PRIVATE)
        if(userlogged.contains("uuid")){
            val action= LoginFragmentDirections.actionLoginFragmentToMainFragment()
            Navigation.findNavController(view).navigate(action)
        }
        val btnregis = view.findViewById<Button>(R.id.btnRegister)
        btnregis.setOnClickListener{
            val action= LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
        val button = view.findViewById<Button>(R.id.btnLogin)
        button.setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.emailEditTextLogin).text.toString()
            val password =  view.findViewById<TextInputEditText>(R.id.passwordEditTextLogin).text.toString()
            userVM.login(email,password)
        }
        observeViewModel(button)
    }
    fun observeViewModel(button:Button){
        userVM.userData.observe(viewLifecycleOwner, Observer {user->
            if(user!=null){
                val sharedPref=requireActivity().getSharedPreferences("UserLogin",Context.MODE_PRIVATE)
                val editor:SharedPreferences.Editor=sharedPref.edit()
                editor.putString("uuid",user.uuid.toString())
                editor.apply()
                val action=LoginFragmentDirections.actionLoginFragmentToMainFragment()
                Navigation.findNavController(button).navigate(action)
            }
            else{
                Toast.makeText(button.context,"Email and Password invalid",Toast.LENGTH_SHORT).show()
            }

        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}