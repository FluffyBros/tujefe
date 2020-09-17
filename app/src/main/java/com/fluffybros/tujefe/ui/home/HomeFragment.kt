package com.fluffybros.tujefe.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fluffybros.tujefe.MainViewModel
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentHomeBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.recyclerHome.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel.homeList.observe(
            viewLifecycleOwner,
            Observer {
                list ->
                binding.recyclerHome.adapter = HomeRecyclerAdapter(list, requireContext(), viewLifecycleOwner)
            }
        )

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
