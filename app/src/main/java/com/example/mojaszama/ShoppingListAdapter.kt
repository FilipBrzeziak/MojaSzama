package com.example.mojaszama

import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mojaszama.databinding.ShoppingListRowBinding


class ShoppingListAdapter(val db: SQLiteDatabase) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, pl: Int): MyViewHolder {
        val contactRow =
            ShoppingListRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MyViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        val count = DatabaseUtils.queryNumEntries(db, ShoppingListDBInfo.TABLE_NAME)
        return count.toInt();
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var name = holder.viewBinding.shoppingListProductName
        val amount = holder.viewBinding.shoppingListAmount
        val amountgml = holder.viewBinding.shoppingListGMl

        val cursor = db.query(
            ShoppingListDBInfo.TABLE_NAME, null, BaseColumns._ID + "=?",
            arrayOf(holder.adapterPosition.plus(1).toString()), null, null, null
        )

        if (cursor.moveToFirst()) {
            name.text = cursor.getString(1)
            amount.text = cursor.getString(2)
            amountgml.text = cursor.getString(3)
        }

        holder.viewBinding.button.setOnClickListener {
            db.delete(
                ShoppingListDBInfo.TABLE_NAME,
                ShoppingListDBInfo.TABLE_COLUMN_PRODUCT + "=\"" + name.text.toString() + "\"",
                null
            )
            notifyItemRemoved(holder.adapterPosition)
        }

        holder.viewBinding.shoppingListCheckBox.setOnClickListener {
            if(holder.viewBinding.shoppingListCheckBox.isChecked){
                TempFridgeDataBase.productList.add(Product(name.text.toString(),amount.text.toString(),amountgml.text.toString()))
            }
            else{
                for(product: Product in TempFridgeDataBase.productList){
                    if(product.productName.equals(name.text.toString())
                        && product.amount.equals(amount.text.toString())
                        && product.amountType.equals(amountgml.text.toString())){
                        TempFridgeDataBase.productList.remove(product)
                    }
                }
            }
        }
    }
}

class MyViewHolder(var viewBinding: ShoppingListRowBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

}