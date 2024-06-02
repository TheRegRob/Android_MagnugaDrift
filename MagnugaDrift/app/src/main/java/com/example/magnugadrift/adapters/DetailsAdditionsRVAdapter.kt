package com.example.magnugadrift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.ui.activities.MagnuItemDetailsActivity

class DetailsAdditionsRVAdapter(private var additionsList: ArrayList<Pair<AggiuntaType, String?>>,
                                private val size: FoodSizes? = null,
                                private val tv_fPrice: TextView) :
    RecyclerView.Adapter<DetailsAdditionsRVAdapter.MenuViewHolder>() {

    private lateinit var additionName: TextView
    private lateinit var additionPrice: TextView
    private lateinit var bt_delete: ImageButton

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
                additionName = itemView.findViewById<TextView>(R.id.tv_additionName)
                additionPrice = itemView.findViewById<TextView>(R.id.tv_additionPrice)
                bt_delete = itemView.findViewById<ImageButton>(R.id.bt_delete_addition)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsAdditionsRVAdapter.MenuViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item_additions,
            parent,
            false
        )
        return MenuViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        return additionsList.size
    }

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int
    ) {
        val addition = additionsList[position]
        if (addition.second == null) {
            additionName.text = addition.first.getName()
        } else {
            additionName.text = addition.second
        }
        additionPrice.text = getMainPrice(addition.first).toString() + "€"
        bt_delete.setOnClickListener(View.OnClickListener {
            var newPrice: Float = MagnuItemDetailsActivity.currentPrice - getMainPrice(additionsList[holder.adapterPosition].first)
            additionsList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            MagnuItemDetailsActivity.currentPrice = newPrice
            tv_fPrice.text = String.format("%.2f", newPrice) + "€"
        })
    }

    private fun getMainPrice(addition: AggiuntaType): Float {
        var _prices = addition.getPrice()
        if (size == null) {
            return _prices[0]
        } else {
            when (size) {
                FoodSizes.S -> {
                    return _prices[0]
                }
                FoodSizes.M -> {
                    return _prices[1]
                }
                FoodSizes.L -> {
                    return _prices[2]
                }
                else -> {
                    return 0.0f
                }
            }
        }
    }
}