package com.fluffybros.tujefe.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentQrScanBinding

class QRScanFragment : Fragment(R.layout.fragment_qr_scan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQrScanBinding.bind(view)

        binding.cameraButton.setOnClickListener {
            
        }
    }
}