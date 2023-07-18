package com.example.midterm_160420014.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.Promos
import com.example.midterm_160420014.R

class PromoListAdapter(val promoList:ArrayList<Promos>): RecyclerView.Adapter<PromoListAdapter.PromoViewHolder>()  {
    class PromoViewHolder(var v: View):RecyclerView.ViewHolder(v)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoListAdapter.PromoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_promo_list,parent,false)
        return PromoListAdapter.PromoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promoList.size
    }

    override fun onBindViewHolder(holder: PromoListAdapter.PromoViewHolder, position: Int) {
        holder.v.let { v->
            v.findViewById<TextView>(R.id.txtUserName).text=promoList[position].code
            v.findViewById<TextView>(R.id.txtReview).text=promoList[position].discount.toString()+" %"
        }
    }

    fun updatepromoList(list:ArrayList<Promos>){
        promoList.clear()
        promoList.addAll(list)
        notifyDataSetChanged()
    }
}