package com.example.hospital

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Manually add the SupportMapFragment to the FrameLayout
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.map, mapFragment)
            .commit()

        // Get the map asynchronously
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val province = intent.getStringExtra("PROVINCE_NAME")

        val location = getProvinceCoordinates(province)
        location?.let {
            mMap.addMarker(MarkerOptions().position(it).title("Location of $province"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 10f))
        }
    }

    private fun getProvinceCoordinates(province: String?): LatLng? {
        return when (province) {
            "DKI Jakarta" -> LatLng(-6.2088, 106.8456)
            "Jawa Barat" -> LatLng(-6.9175, 107.6191)
            else -> null
        }
    }
}
