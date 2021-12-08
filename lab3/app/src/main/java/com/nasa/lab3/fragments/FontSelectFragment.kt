package com.nasa.lab3.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.nasa.lab3.R


class FontSelectFragment : Fragment() {

    interface OnFontSelectedListener {
        fun onFontSelected(text: String, font: String)
    }

    private lateinit var onFontSelectedListener: OnFontSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onFontSelectedListener = context as OnFontSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("Must implement OnFontSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_font_select, container, false)
        val activity = activity as Context

        val et_text = view.findViewById<EditText>(R.id.et_text)
        val radio_group = view.findViewById<RadioGroup>(R.id.radio_group)

        val fonts = mapOf(
            R.id.sans_serif to "sans-serif",
            R.id.casual to "casual",
            R.id.cursive to "cursive"
        )

        val btn_cancel = view.findViewById<Button>(R.id.btn_cancel)
        btn_cancel.setOnClickListener {
            et_text.setText("")
            onFontSelectedListener.onFontSelected("", "")
        }

        val btn_ok = view.findViewById<Button>(R.id.btn_ok)
        btn_ok.setOnClickListener {
            val text = et_text.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(text)) {
                Toast.makeText(activity, "Enter your text!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val font: String = fonts.getValue(radio_group.checkedRadioButtonId)
            onFontSelectedListener.onFontSelected(text, font)
        }

        return view
    }

}