package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.RequestQueue
import com.example.midterm_160420014.model.KulinerDatabase
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope  {
    val userData= MutableLiveData<Users>()
    val updateStatus = MutableLiveData<Boolean>()
    var queue: RequestQueue?=null

    val tag="abc"


    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO

    fun updateProfile(uuid:Int,name:String,email:String,password:String){
        launch {
            val db = buildDB(getApplication())
            val affectedRows = db.userDao().updateProfile(name, email, password, uuid)
            Log.d("NAME: ",name);
            Log.d("EMAIL: ",email);
            Log.d("PASSWORD: ",password);
            Log.d("UUID: ",uuid.toString());
            updateStatus.postValue(affectedRows>0)
        }
    }
    fun getUser(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            userData.postValue(db.userDao().selectById(uuid))
        }
    }
    fun refresh(){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                KulinerDatabase::class.java,"kulinerDB"
            ).build()
            db.userDao().selectAll()
        }
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }

    fun login(email:String,password:String){
        launch {
            val db = buildDB(getApplication())
            val user:Users = db.userDao().login(email,password)
            userData.postValue(user)
        }
    }
}