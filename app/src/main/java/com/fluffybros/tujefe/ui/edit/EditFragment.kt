package com.fluffybros.tujefe.ui.edit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fluffybros.tujefe.MainViewModel
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentEditBinding

class EditFragment: Fragment(R.layout.fragment_edit) {
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditBinding.bind(view)
        val safeArgs: EditFragmentArgs by navArgs()
        val accountPosition = safeArgs.accountNumber
        binding.deleteButton.setOnClickListener {
            val item = mainViewModel.homeList.value!![accountPosition]
            mainViewModel.delete(item) 
            findNavController().navigate(R.id.navigation_home)
        }
    }
}