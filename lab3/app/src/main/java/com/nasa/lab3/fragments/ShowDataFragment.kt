package com.nasa.lab3.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nasa.lab3.R
import com.nasa.lab3.Text

class ShowDataFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_show_data, container, false)
        val data = this.activity?.intent?.getParcelableArrayListExtra<Text>("data")

        val table = view.findViewById<TableLayout>(R.id.table)
        for (item in data!!) {
            val row = TableRow(view.context)
            row.addView(createTextView(item.text, view.context))
            row.addView(createTextView(item.font, view.context))
            table.addView(row)
        }
        return view
    }

    private fun createTextView(string: String, context: Context): TextView {
        val text = TextView(context)
        text.text = string
        return text
    }

}