package com.fluffybros.tujefe

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_recycler_item.view.*

class HomeRecyclerAdapter: RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val accNameView: TextView = itemView.acc_name
        val accCodeView: TextView = itemView.acc_code
        val copyCodeButton: Button = itemView.copy_code_button
        val editAccButton: Button = itemView.edit_acc_button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}