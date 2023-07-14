package com.example.midterm_160420014.View

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.Model.Review
import com.example.midterm_160420014.Model.Users
import com.example.midterm_160420014.R

class ReviewListAdapter(val reviewList:ArrayList<Review> ): RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var v: View):RecyclerView.ViewHolder(v)
    private lateinit var users:Users;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListAdapter.ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_review_list,parent,false)
        return ReviewListAdapter.ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewListAdapter.ReviewViewHolder, position: Int) {
        Log.d("Content 3: ",users.toString())
        holder.v.let { v->
            v.findViewById<TextView>(R.id.txtUserName).text=users.name
            v.findViewById<TextView>(R.id.txtReview).text=reviewList[position].content
        }
    }

    fun updatereviewList(list:ArrayList<Review>,user:Users){
        Log.d("Content 4:",user.toString())
        reviewList.clear()
        reviewList.addAll(list)
        users=user
        notifyDataSetChanged()
    }
}