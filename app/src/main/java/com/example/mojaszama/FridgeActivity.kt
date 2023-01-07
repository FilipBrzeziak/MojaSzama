package com.example.mojaszama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mojaszama.databinding.ActivityFridgeBinding

class FridgeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFridgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFridgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Moja lodówka";
        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase
        binding.fridgeRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.fridgeRecyclerView.adapter = FridgeAdapter(db)

    }
}