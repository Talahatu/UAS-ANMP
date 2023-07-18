package com.example.midterm_160420014.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.example.midterm_160420014.model.Review
import com.example.midterm_160420014.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListReviewViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val reviewList = MutableLiveData<ArrayList<Review>>()
    var queue: RequestQueue?=null

    val tag="abc"
    override val coroutineContext: CoroutineContext
        get() = Job() +Dispatchers.IO

    fun viewReview(resto_id:Int,menu_id:Int){
        launch{
            val db = buildDB(getApplication())
            val review = db.ReviewDao().selectReviewByMenu(resto_id,menu_id)
            reviewList.postValue(review as ArrayList<Review>)
        }
    }
    fun addReview(user_id:Int,menu_id: Int,resto_id: Int,comment:String){
        launch {
            val db= buildDB(getApplication())
            val review=db.ReviewDao().insertReview(Review(resto_id,menu_id,user_id,comment))
        }
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(tag)
    }
}