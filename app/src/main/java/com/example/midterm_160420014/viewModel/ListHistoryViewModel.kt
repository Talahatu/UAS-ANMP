package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.model.History
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListHistoryViewModel(application: Application): AndroidViewModel(application) {
    val historyList = MutableLiveData<ArrayList<History>>()
    var queue: RequestQueue?=null

    val tag="abc"
    fun refreshData(id:Int){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/history.json",{
            val result = Gson().fromJson<ArrayList<History>>(it,object:
                TypeToken<ArrayList<History>>(){}.type)
            val filth:ArrayList<History> = ArrayList<History>()
            result.forEach {data->
                if(data.user_id==id ){
                    filth.add(data)
                }
            }
            historyList.value=filth
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}