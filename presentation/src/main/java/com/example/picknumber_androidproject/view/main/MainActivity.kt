package com.example.picknumber_androidproject.view.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.picknumber_androidproject.R
import com.example.picknumber_androidproject.databinding.ActivityMainBinding
import com.example.picknumber_androidproject.common.ViewBindingActivity
import com.example.picknumber_androidproject.view.search.SearchActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint

// 우리집 좌표
//  "x": "126.9050532",
//  "y": "37.4652659",

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>(), OnMapReadyCallback {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val mapView: MapView by lazy {
        findViewById(R.id.mapView)
    }
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        viewModel.loadBankList()
        updateMarker()

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding.searchButton.setOnClickListener {
            startSearchActivity()
        }
    }

    override fun onMapReady(Map: NaverMap) {
        naverMap = Map

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = true

        locationSource =
            FusedLocationSource(this@MainActivity, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        val cameraUpdate = CameraUpdate
            .scrollAndZoomTo(
                LatLng(
                    naverMap.cameraPosition.target.latitude,
                    naverMap.cameraPosition.target.longitude
                ),
                9.0
            )

        naverMap.moveCamera(cameraUpdate)
    }

    private fun updateMarker() {
        viewModel.bankList.forEach { bank ->
            val marker = Marker()
            marker.position = LatLng(bank.latitude, bank.longitude)
            // TODO : 마커 클릭 리스너
            marker.infoWindow
            marker.map = naverMap
            marker.icon = MarkerIcons.BLACK
            marker.width = Marker.SIZE_AUTO
            marker.height = Marker.SIZE_AUTO
            marker.iconTintColor = Color.BLUE
            // 마커 대신에 infoWindow로 대체해야함
            val infoWindow = InfoWindow()
            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
                override fun getText(infoWindow: InfoWindow): CharSequence {
                    return bank.name
                }
            }
            infoWindow.open(marker)

            marker.isHideCollidedSymbols = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun startSearchActivity() {
        val intent = SearchActivity.getIntent(this).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        }
        startActivity(intent)
    }
}