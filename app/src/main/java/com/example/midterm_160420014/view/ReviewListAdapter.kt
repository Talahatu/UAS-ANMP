package com.example.midterm_160420014.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.Review
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.CardReviewListBinding

class ReviewListAdapter(val reviewList:ArrayList<Review> ): RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var v: CardReviewListBinding):RecyclerView.ViewHolder(v.root)
    private var users:ArrayList<Users> = ArrayList<Users>()
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
                    it.username=user
                    return@forEach
                }
            }
        }
    }

    fun updatereviewList(list:ArrayList<Review>,user:ArrayList<Users>){
        reviewList.clear()
        reviewList.addAll(list)
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }
}