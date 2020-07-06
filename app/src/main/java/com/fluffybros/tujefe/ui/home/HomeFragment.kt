package com.fluffybros.tujefe.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exampleList = generateHomeRecyclerItems()
        val binding = FragmentHomeBinding.bind(view)
        binding.recyclerHome.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = HomeRecyclerAdapter(exampleList)
        }
        binding.floatingActionButton.setOnClickListener {
            // This listener is incomplete
            // TODO: Add functionality for adding an account
            val isVisible = binding.addKeyButton.visibility == View.VISIBLE
            val newVisibility = if (isVisible) View.INVISIBLE else View.VISIBLE
            binding.addKeyButton.visibility = newVisibility
            binding.addQrButton.visibility = newVisibility
        }
    }

    private fun generateHomeRecyclerItems(): List<HomeRecyclerItem> {
        // This is a dummy list
        // TODO: Replace with functionality for retrieving items from a database
        val list = ArrayList<HomeRecyclerItem>()
        for (i in 0 until 100) {
            val item = HomeRecyclerItem(
                i,
                "Account $i",
                "000-000"
            )
            list += item
        }
        return list
    }
}
