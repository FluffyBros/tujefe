package com.fluffybros.tujefe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fluffybros.tujefe.databinding.HomeRecyclerItemBinding

class HomeRecyclerAdapter(private val itemList: List<HomeRecyclerItem>) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    private lateinit var binding: HomeRecyclerItemBinding

    class HomeViewHolder(val binding: HomeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = HomeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.accCode.text = currentItem.accCode
        holder.binding.accName.text = currentItem.accName
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
