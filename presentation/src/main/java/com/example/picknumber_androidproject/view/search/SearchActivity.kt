package com.example.picknumber_androidproject.view.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.picknumber_androidproject.databinding.ActivitySearchBinding
import com.example.picknumber_androidproject.view.common.ViewBindingActivity

class SearchActivity : ViewBindingActivity<ActivitySearchBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySearchBinding
        get() = ActivitySearchBinding::inflate

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(binding.root)
        super.onCreate(savedInstanceState)


    }
}