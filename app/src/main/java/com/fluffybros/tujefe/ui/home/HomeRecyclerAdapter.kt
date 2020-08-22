package com.fluffybros.tujefe.ui.home

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.HomeRecyclerItemBinding
import com.fluffybros.tujefe.db.HomeRecyclerItem
import com.fluffybros.tujefe.ui.edit.EditFragment
import com.fluffybros.tujefe.ui.edit.EditFragmentArgs

class HomeRecyclerAdapter(private val itemList: List<HomeRecyclerItem>, private val activity: FragmentActivity) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    class HomeViewHolder(val binding: HomeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.code.text = currentItem.code
        holder.binding.name.text = currentItem.name
        holder.binding.editButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationEdit(position)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
