package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.RequestQueue
import com.example.midterm_160420014.model.KulinerDatabase
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.util.buildDB
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope  {
    val userData= MutableLiveData<Users?>()
    val updateStatus = MutableLiveData<Boolean>()


    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO


    fun updateSaldo(nominal:Int,uuid:Int){
        launch {
            val db = buildDB(getApplication())
            val affectingRow = db.userDao().updateSaldo(nominal,uuid)
            if(affectingRow>0){
                Log.d("TEST 1","TEST 1")
                updateStatus.postValue(true)
                getUser(uuid)
            }
        }
    }

    fun updateProfile(uuid:Int,name:String,email:String,password:String,alamat:String){
        launch {
            val db = buildDB(getApplication())
            val affectedRows = db.userDao().updateProfile(name, email, password,alamat, uuid)
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
        userData.value=null
    }
    fun clearData(){
        userData.value=null
        Log.d("USERDATA: ",userData.value.toString())
    }

    fun login(email:String,password:String){
        launch {
            val db = buildDB(getApplication())
            val user:Users = db.userDao().login(email,password)
            userData.postValue(user)
        }
    }
    fun register(regisname:String,regisemail:String,regispassword:String){
        launch {
            val db= buildDB(getApplication())
            val status=db.userDao().insert(Users(regisname,regisemail,regispassword,0,""))
        }
    }
}