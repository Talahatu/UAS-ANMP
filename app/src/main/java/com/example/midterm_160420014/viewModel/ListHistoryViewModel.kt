package com.example.midterm_160420014.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.midterm_160420014.model.History
import com.example.midterm_160420014.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.coroutines.CoroutineContext

class ListHistoryViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val historyList = MutableLiveData<ArrayList<History>>()
    val subtotal = MutableLiveData<Int>(0)
    val qty = MutableLiveData<Int>(0)

    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO


    fun refreshData(id:Int){
        launch {
            val db = buildDB(getApplication())
            historyList.postValue(db.historyDao().selectAll(id) as ArrayList<History>?)
        }
    }

    fun checkoutOrder(userID:Int,menuID:Int,quantity:Int,alamat:String,subtotal:Int){
        launch {
            val db = buildDB(getApplication())
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val newHistory = History(quantity,subtotal,userID,menuID,"$year-$month-$day",alamat)
            db.historyDao().insert(newHistory)
            db.userDao().deductSaldo(subtotal,userID)
        }
    }
}