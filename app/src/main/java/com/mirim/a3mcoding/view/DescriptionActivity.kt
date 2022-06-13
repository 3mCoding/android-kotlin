package com.mirim.a3mcoding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {
    lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}