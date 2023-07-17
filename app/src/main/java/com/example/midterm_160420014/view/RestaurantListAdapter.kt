package com.example.midterm_160420014.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.Restaurants
import com.example.midterm_160420014.R
import com.example.midterm_160420014.model.Menus
import com.squareup.picasso.Picasso

class RestaurantListAdapter(val menuList:ArrayList<Menus>):RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {
    class RestaurantViewHolder(var v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_restaurant_list,parent,false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.v.let { v->
            v.findViewById<TextView>(R.id.txtMenuList).text = menuList[position].name
            Picasso.get().load(menuList[position].link).into(v.findViewById<ImageView>(R.id.imgMenuList))
            v.findViewById<Button>(R.id.btnRestoDetail).setOnClickListener {
                Navigation.findNavController(it).navigate(HomeFragmentDirections.actionItemHomeToRestoDetailFragment(menuList[position].uuid))
            }
        }
    }

    fun updateRestoList(list:ArrayList<Menus>){
        menuList.clear()
        menuList.addAll(list)
        notifyDataSetChanged()
    }

}