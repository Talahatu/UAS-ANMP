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

class ListRestoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO

    val menuList = MutableLiveData<ArrayList<Menus>>()
    val errorStatus = MutableLiveData<Boolean>()
    val loadingStatus = MutableLiveData<Boolean>()


    fun refreshData(){
        loadingStatus.value=true
        errorStatus.value=false
        launch {
            val db = buildDB(getApplication())
            val food = db.menuDao().selectAll()
            menuList.postValue(food as ArrayList<Menus>?)
        }

    }

}