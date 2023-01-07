package com.example.mojaszama

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mojaszama.databinding.ActivityShoppingListBinding


class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

        db.execSQL(BasicCommand.SQL_DELETE_TABLE)
        db.execSQL(BasicCommand.SQL_CREATE_TABLE)
        db.execSQL(FrifgeBasicCommand.SQL_DELETE_TABLE)
        db.execSQL(FrifgeBasicCommand.SQL_CREATE_TABLE)
       // list.add(Product("1","1","1"))
       // list.add(Product("2","2","2"))
       // list.add(Product("3","3","3"))


        supportActionBar?.title = "Lista zakupów";

        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.shoppingListRecyclerView.adapter = ShoppingListAdapter(db)

        binding.shoppingListAdd.setOnClickListener {
            val intent = Intent(applicationContext, AddProductActivity::class.java)
            startActivity(intent)

        }
        binding.shoppingListToFridge.setOnClickListener {
            for(product: Product in TempFridgeDataBase.productList){
                val value = ContentValues()
                value.put(FrifgeDBHelper.TABLE_COLUMN_PRODUCT, product.productName)
                value.put(FrifgeDBHelper.TABLE_COLUMN_AMOUNT, product.amount)
                value.put(FrifgeDBHelper.TABLE_COLUMN_AMOUNT_TYPE, product.amountType)

                db.insertOrThrow(FrifgeDBHelper.TABLE_NAME, null, value)
            }
            TempFridgeDataBase.productList.clear()
            val intent = Intent(applicationContext, FridgeActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onResume() {
        binding.shoppingListRecyclerView.adapter?.notifyDataSetChanged()
        super.onResume()
    }
}