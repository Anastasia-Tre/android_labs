package com.nasa.lab3.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nasa.lab3.Database
import com.nasa.lab3.R
import com.nasa.lab3.Text
import com.nasa.lab3.fragments.FontSelectFragment
import com.nasa.lab3.fragments.TextResultFragment

class MainActivity : AppCompatActivity(), FontSelectFragment.OnFontSelectedListener {

    private lateinit var db: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Database(this)

        val btn_open = findViewById<Button>(R.id.btn_open)
        btn_open.setOnClickListener {
            var data = GetText()
            var intent = Intent(this, ShowDataActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }

    }

    override fun onFontSelected(text: String, font: String) {
        val fragment = supportFragmentManager
            .findFragmentById(R.id.textResultFragment) as TextResultFragment

        fragment.setTextResult(text, font)
        if (text.isNotEmpty()) {
            db.addData(text, font)
            Toast.makeText(this, "Text is recorded to DB", Toast.LENGTH_SHORT).show()
        }
    }

    fun GetText(): ArrayList<Text> {
        val array = arrayListOf<Text>()
        val cursor = db.getData()
        cursor!!.moveToFirst()
        val colStringIndex = cursor.getColumnIndexOrThrow(Database.COLUMN_STRING)
        val colFontIndex = cursor.getColumnIndexOrThrow(Database.COLUMN_FONT)

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

        cursor.close()
        return array
    }

    fun GetAllDataFromDB(): ArrayList<Array<String>> {
        val data = arrayListOf<Array<String>>()

        val cursor = db.getData()
        cursor!!.moveToFirst()
        val colStringIndex = cursor.getColumnIndexOrThrow(Database.COLUMN_STRING)
        val colFontIndex = cursor.getColumnIndexOrThrow(Database.COLUMN_FONT)
        var array = arrayOf<String>(
            cursor.getString(colStringIndex),
            cursor.getString(colFontIndex)
        )
        data.add(array)

        while (cursor.moveToNext()) {
            array = arrayOf<String>(
                cursor.getString(colStringIndex),
                cursor.getString(colFontIndex)
            )
            data.add(array)
        }

        cursor.close()
        return data
    }

}