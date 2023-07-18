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
import com.example.midterm_160420014.util.buildDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListMenuViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val menuList = MutableLiveData<ArrayList<Menus>>()

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.IO
    fun refreshData(id:Int){
//        queue= Volley.newRequestQueue(getApplication())
//        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/Menus.json",{
//            val result = Gson().fromJson<ArrayList<Menus>>(it,object:
//                TypeToken<ArrayList<Menus>>(){}.type)
//            val filth:ArrayList<Menus> = ArrayList<Menus>()
//            result.forEach {data->
//                if(data.restoId==id){
//                    filth.add(data)
//                }
//            }
//            menuList.value=filth
//        },{ Log.d("Error",it.toString())})
//        req.tag=tag
//        queue?.add(req)
    }

    fun refreshAll(){
        launch {
            val db = buildDB(getApplication())
            menuList.postValue(db.menuDao().selectAll() as ArrayList<Menus>?)
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}