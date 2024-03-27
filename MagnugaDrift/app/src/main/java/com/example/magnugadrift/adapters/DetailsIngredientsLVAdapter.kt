package com.example.magnugadrift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.magnugadrift.R

class DetailsIngredientsLVAdapter(val ctx: Context, private val ingredientsList: ArrayList<String>) :  BaseAdapter() {
    override fun getCount(): Int {
        return ingredientsList.size
    }

    override fun getItem(p0: Int): Any {
        return ingredientsList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?: LayoutInflater.from(ctx).inflate(R.layout.ingredients_list_item, p2, false)
        val ingredientName = view.findViewById<TextView>(R.id.tv_ingredientName)
        val ingredient = ingredientsList[p0]
        ingredientName.text = ingredient
        return view
    }
}