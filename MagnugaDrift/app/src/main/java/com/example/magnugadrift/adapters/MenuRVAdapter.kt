package com.example.magnugadrift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem
import com.example.magnugadrift.classes.Menu.PizzaNapoletanaMI


class MenuRVAdapter(private val menuList: ArrayList<MagnugaMenuItem>) : RecyclerView.Adapter<MenuRVAdapter.MenuViewHolder>() {
    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuItemImage : ImageView = itemView.findViewById(R.id.ivMenuItemImage)
        val menuItemName : TextView = itemView.findViewById(R.id.tvMenuItemName)
        val menuItemPrice : TextView = itemView.findViewById(R.id.tvMenuItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_row,
            parent,
            false
        )
        return MenuViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentMenuItem = menuList[position]
        holder.menuItemImage.setImageResource(currentMenuItem.getResourceImage())
        holder.menuItemName.text = currentMenuItem.menuItemName()
        holder.menuItemPrice.text = currentMenuItem.menuItemPrice()[0].toString()
    }

}