package com.nasa.lab3.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nasa.lab3.R


class TextResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_result, container, false)
    }

    fun setTextResult(text: String, font: String) {
        val tv_result = requireView().findViewById<TextView>(R.id.tv_result)
        tv_result.typeface = Typeface.create(font, Typeface.NORMAL)
        tv_result.text = text
    }

}