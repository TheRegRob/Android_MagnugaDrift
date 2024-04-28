package com.example.magnugadrift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class NewOrderRVAdapter(private val orderList: ArrayList<MagnugaOrderItem>) :
    RecyclerView.Adapter<NewOrderRVAdapter.NewOrderViewHolder>() {
    private lateinit var mListener: NewOrderRVAdapter.RecyclerViewEvent
    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }
    fun setRecyclerViewEvent(clickListener: NewOrderRVAdapter.RecyclerViewEvent) {
        mListener = clickListener
    }
    inner class NewOrderViewHolder(itemView: View, clickListener: NewOrderRVAdapter.RecyclerViewEvent) : RecyclerView.ViewHolder(itemView){
        val orderItemImage : ImageView = itemView.findViewById(R.id.iv_OrderItemImage)
        val orderItemName : TextView = itemView.findViewById(R.id.tv_OrderItemName)
        val orderItemIngredienti: TextView = itemView.findViewById(R.id.tv_OrderItemIngredients)
        val orderItemAggiunte: TextView = itemView.findViewById(R.id.tv_OrderItemAdditions)
        val orderItemSize: TextView = itemView.findViewById(R.id.tv_OrderItemSize)
        val orderItemPrice : TextView = itemView.findViewById(R.id.tv_OrderItemPrice)
        lateinit var curOrderItem: MagnugaOrderItem

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderRVAdapter.NewOrderViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item_order,
            parent,
            false
        )
        return NewOrderViewHolder(viewLayout, mListener)
    }
    override fun getItemCount(): Int {
        return orderList.size
    }
    override fun onBindViewHolder(holder: NewOrderViewHolder, position: Int) {
        val currentOrderItem = orderList[position]
        holder.curOrderItem = currentOrderItem
        holder.orderItemImage.setImageResource(currentOrderItem.magnugaMenuItem.getResourceImage())
        holder.orderItemName.text = currentOrderItem.magnugaMenuItem.menuItemName()
        holder.orderItemIngredienti.text = getIngredientsString(currentOrderItem.magnugaMenuItem)
        holder.orderItemPrice.text = currentOrderItem.magnugaMenuItem.getCurrentPrice().toString() + "€"
        if (currentOrderItem.magnugaMenuItem.getTaglie() != null) {
            holder.orderItemSize.visibility = View.VISIBLE
            setLabelTxt(holder, currentOrderItem.magnugaMenuItem)
        } else if (currentOrderItem.magnugaMenuItem.getPieces() != null) {
            holder.orderItemSize.visibility = View.VISIBLE
        } else {
            holder.orderItemSize.visibility = View.GONE
        }
    }
    fun getIngredientsString(item: MagnugaMenuItem): String {
        val ingredientsLst = StringBuilder()
        for (i in item.menuItemIngredients())
            ingredientsLst.append(i + ", ")
        var ingredients = ingredientsLst.toString()
        if (ingredients != "") {
            ingredients = ingredients.substring(0, ingredients.length - 2)
        }
        return ingredients
    }
    fun setLabelTxt(holder: NewOrderRVAdapter.NewOrderViewHolder, currentMenuItem: MagnugaMenuItem) {
        when (currentMenuItem.getCurrentSize()) {
            PizzaSizes.PICCOLA -> holder.orderItemSize.text = "Piccola"
            PizzaSizes.MEDIA -> holder.orderItemSize.text = "Media"
            PizzaSizes.MAXI -> holder.orderItemSize.text = "Maxi"
            else -> { holder.orderItemSize.text = "Unexp Err" }
        }
    }
}