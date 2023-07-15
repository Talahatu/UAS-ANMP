package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RestoDetailViewModel(application: Application): AndroidViewModel(application) {
    val restoData= MutableLiveData<Restaurant>()
    var queue: RequestQueue?=null

    val tag="abc"
    fun refresh(id:Int){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/resto.json",{
            val result = Gson().fromJson<ArrayList<Restaurant>>(it,object:TypeToken<ArrayList<Restaurant>>(){}.type)
            result.forEach { data->
                if(data.uuid==id){
                    restoData.value=data
                    return@forEach
                }
            }
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}