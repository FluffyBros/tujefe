package com.fluffybros.tujefe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

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
            when (binding.addKeyButton.visibility) {
                View.INVISIBLE -> {
                    binding.addKeyButton.visibility = View.VISIBLE
                    binding.addQrButton.visibility = View.VISIBLE
                }
                View.VISIBLE -> {
                    binding.addKeyButton.visibility = View.INVISIBLE
                    binding.addQrButton.visibility = View.INVISIBLE
                }
            }
        }
        binding.addKeyButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_manual_add)
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
