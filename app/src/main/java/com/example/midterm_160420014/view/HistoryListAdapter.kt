package com.example.midterm_160420014.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.History
import com.example.midterm_160420014.model.Menus
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.CardHistoryListBinding

class HistoryListAdapter(private var historyList:ArrayList<History>): RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>()  {
    class HistoryViewHolder(var v: CardHistoryListBinding):RecyclerView.ViewHolder(v.root)

    private var menuDatas:ArrayList<Menus> = ArrayList<Menus>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CardHistoryListBinding>(inflater,R.layout.card_history_list,parent,false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            menuDatas.forEach {menu->
                if(menu.uuid==historyList[position].menu_id){
                    holder.v.menu = menuDatas[position]
                    return@forEach
                }
            }
            holder.v.history = historyList[position]

    }

    fun updatehistoryList(list:ArrayList<History>,menus:ArrayList<Menus>){
        historyList.clear()
        historyList.addAll(list)
        menuDatas.clear()
        menuDatas.addAll(menus)
        notifyDataSetChanged()
    }
}