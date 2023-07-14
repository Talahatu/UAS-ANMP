package com.example.midterm_160420014.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.Model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListReviewViewModel(application: Application): AndroidViewModel(application) {
    val reviewList = MutableLiveData<ArrayList<Review>>()
    var queue: RequestQueue?=null

    val tag="abc"
    fun refreshData(resto_id:Int,user_id:String){
        queue= Volley.newRequestQueue(getApplication())
        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/review.json",{
            val result = Gson().fromJson<ArrayList<Review>>(it,object:
                TypeToken<ArrayList<Review>>(){}.type)
            Log.d("Content 2: ",result.toString())
            Log.d("User ID: ",user_id.toString())
            Log.d("Resto ID:",resto_id.toString())
            val filth:ArrayList<Review> = ArrayList<Review>()
            result.forEach {data->
                if(data.userId==user_id.toInt() && data.restoId==resto_id){
                    filth.add(data)
                }
            }
            reviewList.value=filth
        },{ Log.d("Error",it.toString())})
        req.tag=tag
        queue?.add(req)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}