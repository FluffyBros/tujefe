package com.fluffybros.tujefe.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fluffybros.tujefe.R
import kotlinx.android.synthetic.main.home_recycler_item.view.*

class HomeRecyclerAdapter(private val itemList: List<HomeRecyclerItem>) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accNameView: TextView = itemView.acc_name
        val accCodeView: TextView = itemView.acc_code
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.home_recycler_item,
            parent, false
        )

        return HomeViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.accCodeView.text = currentItem.acc_code
        holder.accNameView.text = currentItem.acc_name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
