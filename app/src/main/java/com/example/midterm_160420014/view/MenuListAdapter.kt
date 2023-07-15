package com.example.midterm_160420014.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.Menu
import com.example.midterm_160420014.R
import com.squareup.picasso.Picasso

class MenuListAdapter(val menuList:ArrayList<Menu>): RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>()   {
    class MenuViewHolder(var v: View):RecyclerView.ViewHolder(v)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListAdapter.MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_menu_list,parent,false)
        return MenuListAdapter.MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuListAdapter.MenuViewHolder, position: Int) {
        holder.v.let { v->
            v.findViewById<TextView>(R.id.txtMenuList).text=menuList[position].name
            v.findViewById<TextView>(R.id.txtMenuDescList).text=menuList[position].description
            v.findViewById<TextView>(R.id.txtMenuPriceList).text=menuList[position].price.toString()
            Picasso.get().load(menuList[position].link).into(v.findViewById<ImageView>(R.id.imgMenuList))
        }
    }

    fun updatemenuList(list:ArrayList<Menu>){
        menuList.clear()
        menuList.addAll(list)
        notifyDataSetChanged()
    }
}