package com.example.picknumber_androidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NaverMapSdk.getInstance(this).client = NaverCloudPlatformClient("f1qm5gy16z")
    }
}