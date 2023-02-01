package com.example.picknumber_androidproject.view.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picknumber_androidproject.R
import com.example.picknumber_androidproject.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}