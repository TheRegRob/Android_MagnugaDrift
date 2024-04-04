package com.example.magnugadrift.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R

class DetailsIngredientsRVAdapter(private val ingredientsList: ArrayList<String>) :
    RecyclerView.Adapter<DetailsIngredientsRVAdapter.MenuViewHolder>() {

    /*override fun getCount(): Int {
        return ingredientsList.size
    }*/

    /*override fun getItem(p0: Int): Any {
        return ingredientsList[p0]
    }*/

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
      val ingredientName: TextView  = itemView.findViewById(R.id.tv_ingredientName)
      val bt_exclude: ImageButton = itemView.findViewById(R.id.bt_exclude_ingredient)
        private var ingredientStat: Boolean = true
        init {
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
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsIngredientsRVAdapter.MenuViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.lstview_item_ingredients,
            parent,
            false
        )
        return MenuViewHolder(viewLayout)
    }

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int
    ) {
        val ingredient = ingredientsList[position]
        holder.ingredientName.text = ingredient
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    /*override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
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
    }*/
}