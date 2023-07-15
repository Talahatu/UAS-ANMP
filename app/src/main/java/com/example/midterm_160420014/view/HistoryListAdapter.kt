package com.example.midterm_160420014.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.model.History
import com.example.midterm_160420014.model.Menu
import com.example.midterm_160420014.R

class HistoryListAdapter(val historyList:ArrayList<History>): RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>()  {
    class HistoryViewHolder(var v: View):RecyclerView.ViewHolder(v)

    private var menuDatas:ArrayList<Menu> = ArrayList<Menu>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListAdapter.HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_history_list,parent,false)
        return HistoryListAdapter.HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.v.let { v->
            menuDatas.forEach {
                if(it.id==historyList[position].menu_id){
                    v.findViewById<TextView>(R.id.txtMenuName).text = menuDatas[position].name
                    return@forEach
                }
            }
            v.findViewById<TextView>(R.id.txtNotes).text = historyList[position].notes
            v.findViewById<TextView>(R.id.txtQuantity).text = historyList[position].qty.toString()+" pcs"
            v.findViewById<TextView>(R.id.txtDate).text = historyList[position].date
        }
    }

    fun updatehistoryList(list:ArrayList<History>,menus:ArrayList<Menu>){
        historyList.clear()
        historyList.addAll(list)
        menuDatas.clear()
        menuDatas.addAll(menus)
        notifyDataSetChanged()
    }
}