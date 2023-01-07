package com.example.mojaszama

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FrifgeDBHelper : BaseColumns {
    const val TABLE_NAME = "fridge"
    const val TABLE_COLUMN_PRODUCT = "product"
    const val TABLE_COLUMN_AMOUNT = "amount"
    const val TABLE_COLUMN_AMOUNT_TYPE = "amount_type"

}

object FrifgeBasicCommand {
    const val SQL_CREATE_TABLE =
        "CREATE TABLE ${FrifgeDBHelper.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FrifgeDBHelper.TABLE_COLUMN_PRODUCT} TEXT NOT NULL," +
                "${FrifgeDBHelper.TABLE_COLUMN_AMOUNT} TEXT NOT NULL," +
                "${FrifgeDBHelper.TABLE_COLUMN_AMOUNT_TYPE} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${FrifgeDBHelper.TABLE_NAME}"
}

class FrifgeDataBaseHelper(contex: Context) : SQLiteOpenHelper(contex, FrifgeDBHelper.TABLE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        onCreate(db)
    }

}