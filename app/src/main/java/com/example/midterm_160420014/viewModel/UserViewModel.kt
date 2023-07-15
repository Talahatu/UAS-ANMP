package com.example.midterm_160420014.viewModel

import android.app.Application
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
    var queue: RequestQueue?=null

    val tag="abc"


    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO

    fun getUser(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            db.userDao().selectById(uuid)
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
//        queue= Volley.newRequestQueue(getApplication())
//        val req = StringRequest(Request.Method.GET,"http://10.0.2.2:8080/ANMP/users.json",{
//            val result = Gson().fromJson<ArrayList<Users>>(it,object:
//                TypeToken<ArrayList<Users>>(){}.type)
//            result.forEach { data->
//                if(data.uuid==id.toInt()){
//                    userData.value=data
//                    return@forEach
//                }
//            }
//        },{ Log.d("Error Error Caution!!!",it.toString())})
//        req.tag=tag
//        queue?.add(req)
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