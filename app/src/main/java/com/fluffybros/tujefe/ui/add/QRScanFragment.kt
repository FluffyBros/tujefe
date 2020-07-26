package com.fluffybros.tujefe.ui.add

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fluffybros.tujefe.MainViewModel
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentQrScanBinding


class QRScanFragment : Fragment(R.layout.fragment_qr_scan) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQrScanBinding.bind(view)

        when (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)) {
             PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                try {
                    val intent = Intent("com.google.zxing.client.android.SCAN")
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE") // "PRODUCT_MODE for bar codes
                    startActivityForResult(intent, 0)
                } catch (e: Exception) {
                    val marketUri: Uri = Uri.parse("market://details?id=com.google.zxing.client.android")
                    val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                    startActivity(marketIntent)
                }
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    0)
            }
        }

    }
}