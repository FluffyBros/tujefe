package com.fluffybros.tujefe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fluffybros.tujefe.databinding.HomeRecyclerItemBinding

class HomeRecyclerAdapter(private val itemList: ArrayList<HomeRecyclerItem>) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    class HomeViewHolder(val binding: HomeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = itemList.get(position)
        holder.binding.code.text = currentItem.code
        holder.binding.name.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
