package com.example.midterm_160420014.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.Reviews
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.CardReviewListBinding
import com.example.midterm_160420014.model.Review

class ReviewListAdapter(val reviewList:ArrayList<Review> ): RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var v: CardReviewListBinding):RecyclerView.ViewHolder(v.root)
    private lateinit var users:ArrayList<Users>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListAdapter.ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CardReviewListBinding>(inflater,R.layout.card_review_list,parent,false)
        return ReviewListAdapter.ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewListAdapter.ReviewViewHolder, position: Int) {
        holder.v?.let {
            it.comment = reviewList[position]
            users.forEach {user->
                if(user.uuid==reviewList[position].userId){
                    it.username==user
                }
            }
        }
    }

    fun updatereviewList(list:ArrayList<Review>,user:ArrayList<Users>){
        Log.d("Content 4:",user.toString())
        reviewList.clear()
        reviewList.addAll(list)
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }
}