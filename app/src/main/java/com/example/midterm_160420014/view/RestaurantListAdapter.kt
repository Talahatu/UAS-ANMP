package com.example.midterm_160420014.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.Restaurants
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.CardRestaurantListBinding
import com.example.midterm_160420014.model.Menus
import com.squareup.picasso.Picasso

class RestaurantListAdapter(val menuList:ArrayList<Menus>):RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>(),
    MenuRestaurantItemLayout {
    class RestaurantViewHolder(var v: CardRestaurantListBinding):RecyclerView.ViewHolder(v.root)
    private lateinit var context:Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardRestaurantListBinding.inflate(inflater, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.v.menu = menuList[position]
        holder.v.detailListener = this

    }

    fun updateRestoList(list:ArrayList<Menus>,ctx: Context){
        menuList.clear()
        menuList.addAll(list)
        notifyDataSetChanged()
        context = ctx
    }

    override fun onDetailClick(v: View, menu: Menus) {
        val uuid = context.getSharedPreferences("UserLogin", Context.MODE_PRIVATE).getString("uuid","")!!.toInt()
        val action = HomeFragmentDirections.actionItemHomeToRestoDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}