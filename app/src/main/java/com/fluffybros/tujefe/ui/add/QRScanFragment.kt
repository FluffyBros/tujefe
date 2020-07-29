package com.fluffybros.tujefe.ui.add

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fluffybros.tujefe.MainViewModel
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentQrScanBinding
import com.fluffybros.tujefe.ui.add.camera.*
import com.google.android.gms.vision.MultiProcessor
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class QRScanFragment : Fragment(R.layout.fragment_qr_scan), BarcodeGraphicTracker.BarcodeUpdateListener {
    private val mainViewModel: MainViewModel by activityViewModels()
    private var mCameraSource: CameraSource? = null
    private lateinit var mPreview: CameraSourcePreview
    private lateinit var mGraphicOverlay: GraphicOverlay<BarcodeGraphic>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQrScanBinding.bind(view)

        mGraphicOverlay = binding.graphicOverlay as GraphicOverlay<BarcodeGraphic>
        mPreview = binding.preview

        when (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)) {
            PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                scan()
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
                    scan()
                }
                return
            }
            else -> {
                // Ignore all other requests.
                findNavController().navigate(R.id.navigation_home)
            }
        }
    }

    private fun scan() {
        try {
            val barcodeDetector = BarcodeDetector.Builder(context).build()
            val barcodeFactory = BarcodeTrackerFactory(mGraphicOverlay, requireContext())
            barcodeDetector.setProcessor(
                MultiProcessor.Builder(barcodeFactory).build()
            )
            val builder = CameraSource.Builder(requireContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(15.0f)

            mCameraSource = builder.build()
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    0
                )
                return
            }
            mPreview.start(mCameraSource, mGraphicOverlay)
        } catch (e: Exception) {
            findNavController().navigate(R.id.navigation_home)
        }
    }

    override fun onBarcodeDetected(barcode: Barcode?) {
        //do something with barcode data returned
        Toast.makeText(context,barcode?.displayValue, Toast.LENGTH_SHORT).show()
    }
}
