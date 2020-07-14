package com.fluffybros.tujefe.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fluffybros.tujefe.MainViewModel
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentManualAddBinding

class ManualAddFragment : Fragment(R.layout.fragment_manual_add) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentManualAddBinding.bind(view)

        binding.manualAddButton.setOnClickListener {
            mainViewModel.addRecyclerItem(binding.nameField.editText?.text.toString(), binding.setupKeyField.editText?.text.toString())
            findNavController().navigate(R.id.navigation_home)
        }
    }
}
