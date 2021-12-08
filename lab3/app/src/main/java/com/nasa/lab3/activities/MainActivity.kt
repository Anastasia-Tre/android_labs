package com.nasa.lab3.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nasa.lab3.Database
import com.nasa.lab3.R
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

        }

    }

    override fun onFontSelected(text: String, font: String) {
        val fragment = supportFragmentManager
            .findFragmentById(R.id.textResultFragment) as TextResultFragment

        fragment.setTextResult(text, font)
        if (text.isNotEmpty()) {
            db.addData(text, font)
            val cursor = db.getData()
            cursor!!.moveToFirst()
            val res = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_FONT))
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
        }

    }
}