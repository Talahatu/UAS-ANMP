package com.example.midterm_160420014.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.Model.ApiResponse
import com.example.midterm_160420014.Model.Users
import com.example.midterm_160420014.R
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegisterFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<Button>(R.id.btnSubmitRegister).setOnClickListener {
//            val name = view.findViewById<TextInputEditText>(R.id.nameEditTextRegis).text.toString()
//            val email = view.findViewById<TextInputEditText>(R.id.emailEditTextRegis).text.toString()
//            val pass = view.findViewById<TextInputEditText>(R.id.passwordEditTextRegis).text.toString()
//            val rePass = view.findViewById<TextInputEditText>(R.id.rePassEditTextRegis).text.toString()
//            if(pass == rePass){
//                var req:StringRequest = object:StringRequest(
//                    Request.Method.POST,
//                    "http://10.0.2.2:8080/ANMP/register.php",
//                    {response->
//                        var result = Gson().fromJson(response,ApiResponse::class.java)
//                        if(result.status.toString()=="Success"){
//                            findNavController().popBackStack()
//                        }
//                        else{
//                            Toast.makeText(requireContext(),"User already registered!",Toast.LENGTH_SHORT).show()
//                        }
//                    },{error->
//                        Log.d("Error: ",error.toString())
//                    })
//                {
//                    override fun getParams(): MutableMap<String, String>? {
//                        val params:MutableMap<String,String> = HashMap()
//                        params["name"] = name
//                        params["email"] = email
//                        params["password"] = pass
//                        return params
//                    }
//                }
//                Volley.newRequestQueue(requireContext()).add(req)
//            }
//            else{
//                Toast.makeText(requireContext(),"Password not matched with Retype password!",Toast.LENGTH_SHORT).show();
//            }
//        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

}