package com.nasa.lab3.activities

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nasa.lab3.R
import com.nasa.lab3.Text


class ShowDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        val data = intent.getParcelableArrayListExtra<Text>("data")

        var table = findViewById<TableLayout>(R.id.table)

        for (item in data!!) {
            val row = TableRow(this)
            row.addView(createTextView(item.text))
            row.addView(createTextView(item.font))
            table.addView(row)
        }
    }

    private fun createTextView(string: String): TextView {
        val text = TextView(this)
        text.text = string
        return text
    }

}