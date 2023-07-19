package com.example.midterm_160420014.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.midterm_160420014.model.KulinerDatabase
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope  {
    val userData= MutableLiveData<Users?>()
    val allUserData = MutableLiveData<ArrayList<Users>>()
    val updateStatus = MutableLiveData<Boolean>()


    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO

    fun addAllUserData(){
        launch {
            val db= buildDB(getApplication())
            val users = db.userDao().selectAll()
            allUserData.postValue(users as ArrayList<Users>)
        }
    }

    fun deactivateAccount(user:Users){
        launch {
            val db = buildDB(getApplication())
            db.userDao().deactivateAccount(user)
            db.historyDao().deleteRelatedHistories(user.uuid)
            db.reviewDao().deleteRelatedReview(user.uuid)
        }
    }

    fun updateSaldo(nominal:Int,uuid:Int){
        launch {
            val db = buildDB(getApplication())
            val affectingRow = db.userDao().updateSaldo(nominal,uuid)
            if(affectingRow>0){
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
            db.userDao().insert(Users(regisname,regisemail,regispassword,0,""))
        }
    }
}

