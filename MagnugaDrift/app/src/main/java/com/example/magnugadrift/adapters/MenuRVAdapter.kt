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


class MenuRVAdapter(private val menuList: ArrayList<MagnugaMenuItem>) :
    RecyclerView.Adapter<MenuRVAdapter.MenuViewHolder>() {

    private lateinit var mListener: RecyclerViewEvent
    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }

    fun setRecyclerViewEvent(clickListener: RecyclerViewEvent) {
        mListener = clickListener
    }
    inner class MenuViewHolder(itemView: View, clickListener: RecyclerViewEvent) : RecyclerView.ViewHolder(itemView){
        val menuItemImage : ImageView = itemView.findViewById(R.id.ivMenuItemImage)
        val menuItemName : TextView = itemView.findViewById(R.id.tvMenuItemName)
        val menuItemIngredienti: TextView = itemView.findViewById(R.id.tvMenuItemIngredienti)
        val menuItemPrice : TextView = itemView.findViewById(R.id.tvMenuItemPrice)
        val menuItemSwitch: Button = itemView.findViewById(R.id.bt_size_qnt)
        lateinit var curMenuItem: MagnugaMenuItem

        init {
                itemView.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.orders_recyclerview_row,
            parent,
            false
        )
        return MenuViewHolder(viewLayout, mListener)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentMenuItem = menuList[position]
        holder.curMenuItem = currentMenuItem
        holder.menuItemImage.setImageResource(currentMenuItem.getResourceImage())
        holder.menuItemName.text = currentMenuItem.menuItemName()
        holder.menuItemIngredienti.text = getIngredientsString(currentMenuItem)
        holder.menuItemPrice.text = currentMenuItem.getCurrentPrice().toString() + "€"
        holder.menuItemSwitch.setOnClickListener(View.OnClickListener {
            currentMenuItem.increaseCurrSize()
            refreshRowData(holder, currentMenuItem)
            //notifyItemChanged(position)
        })
        if (currentMenuItem.getTaglie() != null) {
            holder.menuItemSwitch.visibility = View.VISIBLE
            setSwitchButtonTxt(holder, currentMenuItem)
        } else if (currentMenuItem.getPieces() != null) {
            holder.menuItemSwitch.visibility = View.VISIBLE
        } else {
            holder.menuItemSwitch.visibility = View.GONE
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

    fun refreshRowData(holder: MenuViewHolder, currentMenuItem: MagnugaMenuItem) {
        holder.menuItemPrice.text = currentMenuItem.getCurrentPrice().toString() + "€"
        setSwitchButtonTxt(holder, currentMenuItem)
    }

    fun setSwitchButtonTxt(holder: MenuViewHolder, currentMenuItem: MagnugaMenuItem) {
        when (currentMenuItem.getCurrentSize()) {
            PizzaSizes.PICCOLA -> holder.menuItemSwitch.text = "Piccola"
            PizzaSizes.MEDIA -> holder.menuItemSwitch.text = "Media"
            PizzaSizes.MAXI -> holder.menuItemSwitch.text = "Maxi"
            else -> { holder.menuItemSwitch.text = "Unexp Err" }
        }
    }
}