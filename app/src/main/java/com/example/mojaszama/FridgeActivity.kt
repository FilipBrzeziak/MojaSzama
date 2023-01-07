package com.example.mojaszama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mojaszama.databinding.ActivityFridgeBinding

class FridgeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFridgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFridgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Moja lodówka";
    }
}