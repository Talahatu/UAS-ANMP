package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.model.Promos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListPromoViewModel(application: Application): AndroidViewModel(application){
    val promoList = MutableLiveData<ArrayList<Promos>>()
    var queue: RequestQueue?=null

    val tag="abc"
    fun refreshData(id:Int){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/promo.json",{
            val result = Gson().fromJson<ArrayList<Promos>>(it,object:
                TypeToken<ArrayList<Promos>>(){}.type)
            val filth:ArrayList<Promos> = ArrayList<Promos>()
            result.forEach {data->
                if(data.restaurant_id==id){
                    filth.add(data)
                }
            }
            promoList.value=filth
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}