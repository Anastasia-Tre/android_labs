package com.nasa.lab3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_STRING TEXT," +
                "$COLUMN_FONT TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addData(string: String, font: String) {
        val values = ContentValues()
        values.put(COLUMN_STRING, string)
        values.put(COLUMN_FONT, font)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    private fun getData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getArrayListOfText(): ArrayList<Text> {
        val array = arrayListOf<Text>()
        val cursor = getData()
        if (cursor!!.moveToFirst()) {
            val colStringIndex = cursor.getColumnIndexOrThrow(COLUMN_STRING)
            val colFontIndex = cursor.getColumnIndexOrThrow(COLUMN_FONT)

            array.add(
                Text(
                    cursor.getString(colStringIndex),
                    cursor.getString(colFontIndex)
                )
            )

            while (cursor.moveToNext()) {
                array.add(
                    Text(
                        cursor.getString(colStringIndex),
                        cursor.getString(colFontIndex)
                    )
                )
            }
        } else {
            array.add(
                Text("no data", "")
            )
        }

        cursor.close()
        return array
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"
        const val TABLE_NAME = "data"
        const val COLUMN_STRING = "string"
        const val COLUMN_FONT = "font"
    }
}