package com.example.mojaszama

import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mojaszama.databinding.ActivityShoppingListBinding

class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    val list: ArrayList<Product> = arrayListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DataBaseHelper(applicationContext)
        val shoppingListDB = dbHelper.writableDatabase
        shoppingListDB.execSQL(BasicCommand.SQL_DELETE_TABLE)
        shoppingListDB.execSQL(BasicCommand.SQL_CREATE_TABLE)
       // list.add(Product("1","1","1"))
       // list.add(Product("2","2","2"))
       // list.add(Product("3","3","3"))


        supportActionBar?.title = "Lista zakupów";

        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.shoppingListRecyclerView.adapter = ShoppingListAdapter(shoppingListDB)

        binding.shoppingListAdd.setOnClickListener {
            val intent = Intent(applicationContext, AddProductActivity::class.java)
            startActivity(intent)

        }
        binding.shoppingListToFridge.setOnClickListener {
            val intent = Intent(applicationContext, FridgeActivity::class.java)
            startActivity(intent)

        }

    }
    override fun onResume() {
        super.onResume()
        val dbHelper = DataBaseHelper(applicationContext)
        val shoppingListDB = dbHelper.writableDatabase

        binding.shoppingListRecyclerView.adapter =
            ShoppingListAdapter(shoppingListDB)
    }
}