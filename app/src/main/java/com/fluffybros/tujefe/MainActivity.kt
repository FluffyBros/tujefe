package com.fluffybros.tujefe

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fluffybros.tujefe.ui.add.camera.BarcodeGraphicTracker
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
        // do something with barcode data returned
        if (barcode != null) {
            Log.d("Barcode-Reader", barcode.displayValue)
            Log.d("Barcode-Reader", barcode.format.toString())

            val secretSearch = ArrayList<String>()
            secretSearch.add("secret=")
            val codeStart = barcode.displayValue.findAnyOf(secretSearch, 0, false)
            val secretEndSearch = ArrayList<String>()
            secretEndSearch.add("&")
            val codeEnd = barcode.displayValue.findAnyOf(secretEndSearch, codeStart?.first ?: 0, false)
            val codeIndices = IntRange(codeStart?.first?.plus(7) ?: 0, codeEnd?.first?.minus(1) ?: barcode.displayValue.lastIndex)
            val code = barcode.displayValue.substring(codeIndices)

            val issuerSearch = ArrayList<String>()
            issuerSearch.add("issuer=")
            val nameStart = barcode.displayValue.findAnyOf(issuerSearch, 0, false)
            val issuerEndSearch = ArrayList<String>()
            issuerEndSearch.add("&")
            val nameEnd = barcode.displayValue.findAnyOf(issuerEndSearch, nameStart?.first ?: 0, false)
            val nameIndices = IntRange(nameStart?.first?.plus(7) ?: 0, nameEnd?.first?.minus(1) ?: barcode.displayValue.lastIndex)
            val name = barcode.displayValue.substring(nameIndices)

            mainViewModel.addRecyclerItem(name, code)
            runOnUiThread { navController.navigate(R.id.navigation_home) }
        }
    }
}
