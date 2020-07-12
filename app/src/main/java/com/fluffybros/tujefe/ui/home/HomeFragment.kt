package com.fluffybros.tujefe.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fluffybros.tujefe.MainViewModel
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exampleList = mainViewModel.homeList.value
        val binding = FragmentHomeBinding.bind(view)

        binding.recyclerHome.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = HomeRecyclerAdapter(exampleList?:ArrayList())
        }
        binding.floatingActionButton.setOnClickListener {
            // This listener is incomplete
            // TODO: Add functionality for adding an account
            val isVisible = binding.addKeyButton.visibility == View.VISIBLE
            val newVisibility = if (isVisible) View.INVISIBLE else View.VISIBLE
            binding.addKeyButton.visibility = newVisibility
            binding.addQrButton.visibility = newVisibility
        }
        binding.addKeyButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_manual_add)
        }
    }
}
