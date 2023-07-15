package com.example.midterm_160420014.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.Model.Users
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserViewModel(application: Application): AndroidViewModel(application)  {
    val userData= MutableLiveData<Users>()
    var queue: RequestQueue?=null

    val tag="abc"
    fun refresh(id:String){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/users.json",{
            val result = Gson().fromJson<ArrayList<Users>>(it,object:
                TypeToken<ArrayList<Users>>(){}.type)
            result.forEach { data->
                if(data.id==id){
                    userData.value=data
                    return@forEach
                }
            }
        },{ Log.d("Error Error Caution!!!",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}