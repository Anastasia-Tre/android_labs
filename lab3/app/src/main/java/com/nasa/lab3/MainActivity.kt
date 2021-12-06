package com.nasa.lab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FontSelectFragment.OnFontSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFontSelected(text: String, font: String) {
        val fragment = supportFragmentManager
            .findFragmentById(R.id.textResultFragment) as TextResultFragment

        fragment.setTextResult(text, font)
    }
}