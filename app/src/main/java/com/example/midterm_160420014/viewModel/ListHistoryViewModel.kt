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
import com.example.midterm_160420014.util.buildDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListHistoryViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val historyList = MutableLiveData<ArrayList<History>>()
    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO
    fun refreshData(id:Int){
        launch {
            val db = buildDB(getApplication())
            historyList.postValue(db.historyDao().selectAll(id) as ArrayList<History>?)
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}