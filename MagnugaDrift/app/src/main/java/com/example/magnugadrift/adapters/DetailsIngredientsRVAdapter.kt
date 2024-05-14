package com.example.magnugadrift.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R

class DetailsIngredientsRVAdapter(private val ingredientsList: ArrayList<Pair<String, Boolean>>) :
    RecyclerView.Adapter<DetailsIngredientsRVAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ingredientName: TextView  = itemView.findViewById(R.id.tv_ingredientName)
        val bt_exclude: ImageButton = itemView.findViewById(R.id.bt_exclude_ingredient)
        init {
            for(i in 0 ..< ingredientsList.count()) {
                SetupLstValues(this, ingredientsList[i].second)
            }
            bt_exclude.setOnClickListener {
                if (ingredientsList[adapterPosition].second) {
                    bt_exclude.setImageResource(R.drawable.ic_add)
                    ingredientsList[adapterPosition] = ingredientsList[adapterPosition].copy(second = false)
                    ingredientName.paintFlags = ingredientName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    bt_exclude.setImageResource(R.drawable.ic_remove)
                    ingredientsList[adapterPosition] = ingredientsList[adapterPosition].copy(second = true)
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
            R.layout.recyclerview_item_ingredients,
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
        holder.ingredientName.text = ingredient.first
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    private fun SetupLstValues(holder: MenuViewHolder, status: Boolean) {
        if (!status) {
            holder.bt_exclude.setImageResource(R.drawable.ic_add)
            holder.ingredientName.paintFlags = holder.ingredientName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.bt_exclude.setImageResource(R.drawable.ic_remove)
            holder.ingredientName.paintFlags = holder.ingredientName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}