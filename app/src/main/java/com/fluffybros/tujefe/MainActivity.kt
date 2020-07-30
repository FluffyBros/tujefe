package com.fluffybros.tujefe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.telephony.MbmsDownloadSession.RESULT_CANCELLED
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.fragment.app.add
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fluffybros.tujefe.db.HomeRecyclerItem
import com.fluffybros.tujefe.ui.add.QRScanFragment
import com.fluffybros.tujefe.ui.add.camera.BarcodeGraphicTracker
import com.fluffybros.tujefe.ui.home.HomeFragment
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BarcodeGraphicTracker.BarcodeUpdateListener {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBarcodeDetected(barcode: Barcode?) {
        //do something with barcode data returned
        if (barcode != null) {
            Log.d("Barcode-Reader", barcode.displayValue)
            Log.d("Barcode-Reader", barcode.format.toString())

            mainViewModel.addRecyclerItem(barcode.displayValue, barcode.rawValue)
            runOnUiThread { navController.navigate(R.id.navigation_home) }
        }
    }
}
