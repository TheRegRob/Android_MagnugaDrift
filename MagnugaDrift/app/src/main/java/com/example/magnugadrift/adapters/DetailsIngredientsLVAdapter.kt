package com.example.magnugadrift.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.magnugadrift.R

class DetailsIngredientsLVAdapter(val ctx: Context, private val ingredientsList: ArrayList<String>) :  BaseAdapter() {

    private var ingredientStat: Boolean = true

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
        val view = p1 ?: LayoutInflater.from(ctx).inflate(R.layout.lstview_item_ingredients, p2, false)
        val ingredientName = view.findViewById<TextView>(R.id.tv_ingredientName)
        val ingredient = ingredientsList[p0]
        ingredientName.text = ingredient
        val bt_exclude = view.findViewById<ImageButton>(R.id.bt_exclude_ingredient)
        bt_exclude.setOnClickListener {
            if (ingredientName.paintFlags == ingredientName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()) {
                bt_exclude.setImageResource(R.drawable.ic_add)
                ingredientStat = false
                ingredientName.paintFlags = ingredientName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                bt_exclude.setImageResource(R.drawable.ic_remove)
                ingredientStat = true
                ingredientName.paintFlags = ingredientName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        return view
    }
}