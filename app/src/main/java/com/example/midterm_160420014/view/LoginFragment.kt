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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.R
import com.example.midterm_160420014.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginFragment : Fragment() {

    private lateinit var userVM:UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        userVM.refresh()






        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.emailEditTextLogin).text.toString()
            val password =  view.findViewById<TextInputEditText>(R.id.passwordEditTextLogin).text.toString()
            val req = StringRequest(
                Request.Method.GET,
                "http://10.0.2.2:8080/ANMP/users.json",
                { response ->
                    Log.d("Response: ",response)
                    val sType = object: TypeToken<ArrayList<Users>>(){}.type
                    val result = Gson().fromJson<ArrayList<Users>>(response,sType)
                    result.forEach {data->
                        if(data.email==email && data.password==password){
                            val sharedPref = requireActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE)
                            val editor:SharedPreferences.Editor = sharedPref.edit()
                            editor.putString("id",data.uuid.toString())
                            editor.apply()
                            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                            Navigation.findNavController(it).navigate(action)
                            return@forEach
                        }
                    }
                },{ error -> Log.d("Error: ",error.toString())})
            Log.d("Request: ",req.toString())
            Volley.newRequestQueue(requireContext()).add(req)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}