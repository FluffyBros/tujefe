package com.fluffybros.tujefe.ui.add

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentQrScanBinding

class QRScanFragment : Fragment(R.layout.fragment_qr_scan) {
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQrScanBinding.bind(view)

        binding.cameraButton.setOnClickListener {
            when (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)) {
                PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    dispatchTakePictureIntent()
                }
                else -> {
                    // You can directly ask for the permission.
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        0
                    )
                }
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            0 -> {
                // If request is cancelled, the result arrays are empty.
                if ((
                            grantResults.isNotEmpty() &&
                                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                            )
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    dispatchTakePictureIntent()
                }
                return
            }
            else -> {
                // Ignore all other requests.
                findNavController().navigate(R.id.navigation_home)
            }
        }
    }
}