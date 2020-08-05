package com.fluffybros.tujefe

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode==1) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val barcode = decodeBitmap(imageBitmap)
            if(barcode == null){
                //TODO: let user know nothing was detected
                return
            }
            //Parse the data
            if (barcode.displayValue.contains("secret=")) {
                val secretSearch = ArrayList<String>()
                secretSearch.add("secret=")
                val codeStart = barcode.displayValue.findAnyOf(secretSearch, 0, false)
                val secretEndSearch = ArrayList<String>()
                secretEndSearch.add("&")
                val codeEnd =
                    barcode.displayValue.findAnyOf(secretEndSearch, codeStart?.first ?: 0, false)
                val codeIndices = IntRange(
                    codeStart?.first?.plus(7) ?: 0,
                    codeEnd?.first?.minus(1) ?: barcode.displayValue.lastIndex
                )
                val code = barcode.displayValue.substring(codeIndices)

                val issuerSearch = ArrayList<String>()
                issuerSearch.add("issuer=")
                val nameStart = barcode.displayValue.findAnyOf(issuerSearch, 0, false)
                val issuerEndSearch = ArrayList<String>()
                issuerEndSearch.add("&")
                val nameEnd =
                    barcode.displayValue.findAnyOf(issuerEndSearch, nameStart?.first ?: 0, false)
                val nameIndices = IntRange(
                    nameStart?.first?.plus(7) ?: 0,
                    nameEnd?.first?.minus(1) ?: barcode.displayValue.lastIndex
                )
                val name = barcode.displayValue.substring(nameIndices)

                mainViewModel.addRecyclerItem(name, code)
            }
//            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun decodeBitmap(bitmap: Bitmap): Barcode?{
        val detector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.DATA_MATRIX or Barcode.QR_CODE)
            .build()
        if (!detector.isOperational) {
            // TODO: tell user that something went wrong
            return null
        }
        val frame: Frame = Frame.Builder().setBitmap(bitmap).build()
        val barcodes = detector.detect(frame)
        return barcodes.valueAt(0)
    }
}
