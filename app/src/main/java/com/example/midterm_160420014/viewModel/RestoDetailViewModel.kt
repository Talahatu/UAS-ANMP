package com.example.midterm_160420014.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.midterm_160420014.model.Menus
import com.example.midterm_160420014.model.Restaurants
import com.example.midterm_160420014.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RestoDetailViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

    val menuList = MutableLiveData<Menus>()
    val restoList = MutableLiveData<Restaurants>()
    val errorStatus = MutableLiveData<Boolean>()
    val loadingStatus = MutableLiveData<Boolean>()


    fun refresh(id:Int){
        loadingStatus.value=true
        errorStatus.value=false
        launch {
            val db = buildDB(getApplication())
            val food = db.menuDao().selectById(id)
            val resto = db.restaurantDao().selectById(food.restoId)
            menuList.postValue(food)
            restoList.postValue(resto)
        }
    }

    fun getMenu(id:Int){
        launch {
            val db = buildDB(getApplication())
            val food = db.menuDao().selectById(id)
            menuList.postValue(food)
        }
    }


}