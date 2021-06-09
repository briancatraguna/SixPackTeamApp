package com.bangkit.responderapp.ui.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.responderapp.R
import com.bangkit.responderapp.databinding.ActivityMapsBinding
import com.bangkit.responderapp.ui.DetailReportActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        latitude = intent.getDoubleExtra(DetailReportActivity.LATITUDE_KEY,0.0)
        longitude = intent.getDoubleExtra(DetailReportActivity.LONGITUDE_KEY,0.0)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val yourLocation = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(yourLocation).title("Reporter's Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourLocation,15f))
    }
}