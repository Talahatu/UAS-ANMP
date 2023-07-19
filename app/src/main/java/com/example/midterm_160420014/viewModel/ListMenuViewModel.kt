package com.example.midterm_160420014.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.midterm_160420014.model.Menus
import com.example.midterm_160420014.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListMenuViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val menuList = MutableLiveData<ArrayList<Menus>>()

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.IO

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