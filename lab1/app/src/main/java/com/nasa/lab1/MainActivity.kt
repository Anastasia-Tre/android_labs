package com.nasa.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import android.text.TextUtils
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et_text = findViewById<EditText>(R.id.et_text)
        val tv_result = findViewById<TextView>(R.id.tv_result)
        val radio_group = findViewById<RadioGroup>(R.id.radio_group)

        val fonts = mapOf(
            R.id.sans_serif to "sans-serif",
            R.id.casual to "casual",
            R.id.cursive to "cursive"
        )

        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        btn_cancel.setOnClickListener {
            et_text.setText("")
            tv_result.setText("")
        }

        val btn_ok = findViewById<Button>(R.id.btn_ok)
        btn_ok.setOnClickListener {
            val text = et_text.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "Enter your text!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val font = fonts.getValue(radio_group.checkedRadioButtonId)
            tv_result.typeface = Typeface.create(font, Typeface.NORMAL)
            tv_result.text = text
        }

    }
}