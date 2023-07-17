package com.example.midterm_160420014.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.midterm_160420014.R
import com.example.midterm_160420014.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {
    private lateinit var userVM: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM= ViewModelProvider(this)[UserViewModel::class.java]
        val btnRegister = view.findViewById<Button>(R.id.btnSubmitRegister)
        btnRegister.setOnClickListener{
            var username = view.findViewById<TextInputEditText>(R.id.nameEditTextRegis).text.toString();
            var email = view.findViewById<TextInputEditText>(R.id.emailEditTextRegis).text.toString();
            var password = view.findViewById<TextInputEditText>(R.id.passwordEditTextRegis).text.toString();
            var repassword = view.findViewById<TextInputEditText>(R.id.rePassEditTextRegis).text.toString();
            if(password==repassword){
                userVM.register(username,email,password)
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                Navigation.findNavController(it).navigate(action)
                Toast.makeText(it.context,"Register complete", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(it.context,"Password doesn't match", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

}