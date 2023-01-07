package com.example.mojaszama

import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mojaszama.databinding.FridgeRowBinding


class FridgeAdapter(val db: SQLiteDatabase) :
    RecyclerView.Adapter<FridgeMyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, pl: Int): FridgeMyViewHolder {
        val contactRow =
            FridgeRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FridgeMyViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        val count = DatabaseUtils.queryNumEntries(db, FrifgeDBHelper.TABLE_NAME)
        return count.toInt();
    }

    override fun onBindViewHolder(holder: FridgeMyViewHolder, position: Int) {
        var name = holder.viewBinding.fridgeText
        val amount = holder.viewBinding.fridgeValAmount
        val amountgml = holder.viewBinding.fridgeAmountGML

        val cursor = db.query(
            FrifgeDBHelper.TABLE_NAME, null, BaseColumns._ID + "=?",
            arrayOf(holder.adapterPosition.plus(1).toString()), null, null, null
        )

        if (cursor.moveToFirst()) {
            name.text = cursor.getString(1)
            amount.text = cursor.getString(2)
            amountgml.text = cursor.getString(3)
        }

        holder.viewBinding.button.setOnClickListener {
            db.delete(
                FrifgeDBHelper.TABLE_NAME,
                FrifgeDBHelper.TABLE_COLUMN_PRODUCT + "=\"" + name.text.toString() + "\"",
                null
            )
            notifyItemRemoved(holder.adapterPosition)
        }

        holder.viewBinding.fridgeCheckBox.setOnClickListener {
//            if(holder.viewBinding.shoppingListCheckBox.isChecked){
//                TempFridgeDataBase.productList.add(Product(name.text.toString(),amount.text.toString(),amountgml.text.toString()))
//            }
//            else{
//                for(product: Product in TempFridgeDataBase.productList){
//                    if(product.productName.equals(name.text.toString())
//                        && product.amount.equals(amount.text.toString())
//                        && product.amountType.equals(amountgml.text.toString())){
//                        TempFridgeDataBase.productList.remove(product)
//                    }
//                }
//            }
        }
    }
}

class FridgeMyViewHolder(var viewBinding: FridgeRowBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

}