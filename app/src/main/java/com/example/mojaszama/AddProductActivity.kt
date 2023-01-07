package com.example.mojaszama

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mojaszama.databinding.ActivityAddProductBinding


class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private var id: Long? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Dodaj produkt"
        val dbHelper = DataBaseHelper(applicationContext)
        val shoppingListDB = dbHelper.writableDatabase


        binding.addProduct.setOnClickListener{

            val productName = binding.nazwaProduktu.text.toString()
            val amount = binding.ilosc.text.toString()
            var amountType = ""
            if(binding.radioSzt.id==binding.amountTypes.checkedRadioButtonId){
                amountType = binding.radioSzt.text.toString()
            }
            else if(binding.radioG.id==binding.amountTypes.checkedRadioButtonId){
                amountType = binding.radioG.text.toString()
            }
            else if(binding.radioMl.id==binding.amountTypes.checkedRadioButtonId){
                amountType = binding.radioMl.text.toString()
            }

            if (!productName.isNullOrEmpty() && !amount.isNullOrEmpty() && !amountType.isNullOrEmpty()) {
                val value = ContentValues()
                value.put(ShoppingListDBInfo.TABLE_COLUMN_PRODUCT, productName)
                value.put(ShoppingListDBInfo.TABLE_COLUMN_AMOUNT, amount)
                value.put(ShoppingListDBInfo.TABLE_COLUMN_AMOUNT_TYPE, amountType)

                shoppingListDB.insertOrThrow(ShoppingListDBInfo.TABLE_NAME, null, value)
                finish()
            }
            else{
                val infoToast = Toast.makeText(applicationContext, "Uzupelnij dane!", Toast.LENGTH_SHORT)
                infoToast.show()
            }
       }
    }
}