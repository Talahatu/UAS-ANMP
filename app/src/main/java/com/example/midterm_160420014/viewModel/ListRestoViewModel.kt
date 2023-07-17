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
import com.example.midterm_160420014.model.Restaurants
import com.example.midterm_160420014.util.buildDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListRestoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO

    val menuList = MutableLiveData<ArrayList<Menus>>()
    val errorStatus = MutableLiveData<Boolean>()
    val loadingStatus = MutableLiveData<Boolean>()

    val tag="abc"
    var queue:RequestQueue?=null

    fun refreshData(){
        loadingStatus.value=true
        errorStatus.value=false
        launch {
            val db = buildDB(getApplication())
            val food = db.menuDao().selectAll()
            menuList.postValue(food as ArrayList<Menus>?)
        }

    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }

}