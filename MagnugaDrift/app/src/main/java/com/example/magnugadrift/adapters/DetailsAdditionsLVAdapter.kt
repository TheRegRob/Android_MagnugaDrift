package com.example.magnugadrift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.AggiuntaType

class DetailsAdditionsLVAdapter(val ctx: Context, private val additionsList: ArrayList<AggiuntaType>) :  BaseAdapter()  {
    override fun getCount(): Int {
        return additionsList.size
    }

    override fun getItem(p0: Int): Any {
        return additionsList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?: LayoutInflater.from(ctx).inflate(R.layout.additions_list_item, p2, false)
        val additionName = view.findViewById<TextView>(R.id.tv_additionName)
        val additionPrice = view.findViewById<TextView>(R.id.tv_additionPrice)
        val addition = additionsList[p0]
        additionName.text = addition.getName()
        additionPrice.text = addition.getPrice().toString() + "€"
        return view
    }
}