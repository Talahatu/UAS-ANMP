package com.example.midterm_160420014.ViewModel

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.Model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListRestoViewModel(application: Application):AndroidViewModel(application) {
    val restoList = MutableLiveData<ArrayList<Restaurant>>()
    val errorStatus = MutableLiveData<Boolean>()
    val loadingStatus = MutableLiveData<Boolean>()

    val tag="abc"
    var queue:RequestQueue?=null

    fun refreshData(){
        loadingStatus.value=true
        errorStatus.value=false
        queue=Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/resto.json",{
            val result = Gson().fromJson<ArrayList<Restaurant>>(it,object:TypeToken<ArrayList<Restaurant>>(){}.type)
            Log.d("Test Content: ",result.toString())
            restoList.value=result
            errorStatus.value=false
            loadingStatus.value=false
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }

}