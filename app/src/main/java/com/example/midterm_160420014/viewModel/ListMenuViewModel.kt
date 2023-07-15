package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.model.Menus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListMenuViewModel(application: Application): AndroidViewModel(application) {
    val menuList = MutableLiveData<ArrayList<Menus>>()
    var queue: RequestQueue?=null

    val tag="abc"
    fun refreshData(id:Int){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/Menus.json",{
            val result = Gson().fromJson<ArrayList<Menus>>(it,object:
                TypeToken<ArrayList<Menus>>(){}.type)
            val filth:ArrayList<Menus> = ArrayList<Menus>()
            result.forEach {data->
                if(data.restoId==id){
                    filth.add(data)
                }
            }
            menuList.value=filth
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }

    fun refreshAll(){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/Menus.json",{
            val result = Gson().fromJson<ArrayList<Menus>>(it,object:
                TypeToken<ArrayList<Menus>>(){}.type)
            menuList.value=result
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}