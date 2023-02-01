package com.example.picknumber_androidproject.view.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.data.model.bank.BankDto
import com.example.data.model.bank.BankListDto
import com.example.data.api.BankApi
import com.example.data.api.Direction5Api
import com.example.data.model.directions5.DirectionsDto
import com.example.picknumber_androidproject.R
import com.example.picknumber_androidproject.databinding.ActivityMainBinding
import com.example.picknumber_androidproject.view.common.ViewBindingActivity
import com.example.picknumber_androidproject.view.search.SearchActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// 우리집 좌표
//  "x": "126.9050532",
//  "y": "37.4652659",

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

        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

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

        naverMap.cameraPosition.target.latitude
        naverMap.cameraPosition.target.longitude

        val cameraUpdate = CameraUpdate.scrollTo(
            LatLng(
                naverMap.cameraPosition.target.latitude,
                naverMap.cameraPosition.target.longitude
            )
        )
        naverMap.moveCamera(cameraUpdate)


        getBankListFromAPI()
        getBankListDistanceFromAPI(naverMap.cameraPosition.target)

        //127.2566183,37.0095927 // 안성 본
    }

    private fun getBankListDistanceFromAPI(target: LatLng) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://naveropenapi.apigw.ntruss.com/map-direction/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val myPosition = target.longitude.toString() + "," + target.latitude.toString()
        val tempPosition = "127.2566183,37.0095927"

        retrofit.create(Direction5Api::class.java).also {
            it.getDistance(
                "f1qm5gy16z",
                "PJ5FKlGtMjQ4lwZLVSPul185mSDiKprEaWjCYtpI",
                start = myPosition,
                goal = tempPosition
            )
                .enqueue(object : Callback<DirectionsDto> {
                    override fun onResponse(
                        call: Call<DirectionsDto>,
                        response: Response<DirectionsDto>
                    ) {
                        if (response.isSuccessful.not()) {
                            Log.d("실패", response.toString())
                            return
                        } else {
                            response.body()?.routeDto?.traoptimalDto?.get(0)?.summaryDto?.distance.let { it1 ->
                                Log.d(
                                    "거리 출력", it1.toString()
                                )
                            }
                            response.body()?.toString()?.let { it1 -> Log.d("성공", it1) }
                        }
                    }

                    override fun onFailure(call: Call<DirectionsDto>, t: Throwable) {
                    }

                })
        }
    }

    private fun getBankListFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BankApi::class.java).also { it ->
            it.getBankList()
                .enqueue(object : Callback<BankListDto> {
                    override fun onResponse(
                        call: Call<BankListDto>,
                        response: Response<BankListDto>
                    ) {
                        if (response.isSuccessful.not()) {
                            // 실패 처리에 대한 구현
                            return
                        }
                        response.body()?.let { dto ->
                            updateMarker(dto.items)
                        }
                    }

                    override fun onFailure(call: Call<BankListDto>, t: Throwable) {
                        // 실패 처리에 대한 구현
                    }
                })
        }
    }

    private fun updateMarker(banks: List<BankDto>) {
        banks.forEach { bank ->
            Log.d("Banks", bank.toString())
            val marker = Marker()
            marker.position = LatLng(bank.latitude, bank.longitude)
            // TODO : 마커 클릭 리스너
            marker.map = naverMap
            marker.tag = bank.code
            marker.icon = MarkerIcons.BLACK
            marker.width = Marker.SIZE_AUTO
            marker.height = Marker.SIZE_AUTO
            marker.iconTintColor = Color.BLUE
            marker.captionText = bank.name + " 새마을금고 " + bank.divisionName
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
        val intent = SearchActivity.getIntent(this)
        startActivity(intent)
    }
}