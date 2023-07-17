package com.example.midterm_160420014.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.midterm_160420014.R
import com.example.midterm_160420014.viewModel.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var userVM: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnRegister = view.findViewById<Button>(R.id.btnSubmitRegister)
        var username = view.findViewById<EditText>(R.id.nameEditTextRegis).text.toString();
        var email = view.findViewById<EditText>(R.id.emailEditTextRegis).text.toString();
        var password = view.findViewById<EditText>(R.id.passwordEditTextRegis).text.toString();
        var repassword = view.findViewById<EditText>(R.id.rePassEditTextRegis).text.toString();
        btnRegister.setOnClickListener{
            if(password==repassword){
                userVM.register(username,email,password)
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