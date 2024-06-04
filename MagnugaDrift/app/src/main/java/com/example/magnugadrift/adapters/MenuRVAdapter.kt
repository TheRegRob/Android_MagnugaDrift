package com.example.magnugadrift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem


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
        val menuItemImage : ImageView = itemView.findViewById(R.id.RecyclerviewItemMenu_iv_ItemImage)
        val menuItemName : TextView = itemView.findViewById(R.id.RecyclerviewItemMenu_tv_ItemName)
        val menuItemIngredienti: TextView = itemView.findViewById(R.id.RecyclerviewItemMenu_tv_ItemIngredients)
        val menuItemPrice : TextView = itemView.findViewById(R.id.RecyclerviewItemMenu_tv_ItemPrice)
        val menuItemSwitch: Button = itemView.findViewById(R.id.RecyclerviewItemMenu_bt_Size)
        val iv_FoodType: ImageView = itemView.findViewById(R.id.RecyclerviewItemMenu_iv_ItemType)
        val ll_FoodType: LinearLayout = itemView.findViewById(R.id.RecyclerviewItemMenu_ll_ItemType)
        lateinit var curMenuItem: MagnugaMenuItem

        init {
                itemView.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.recycview_item_menu,
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
        if (currentMenuItem.menuItemIngredients() != null) {
            holder.menuItemIngredienti.visibility = View.VISIBLE
            holder.menuItemIngredienti.text = getIngredientsString(currentMenuItem)
        } else {
            holder.menuItemIngredienti.visibility = View.GONE
        }
        holder.menuItemPrice.text = currentMenuItem.getCurrentPrice().toString() + "€"
        holder.menuItemSwitch.setOnClickListener(View.OnClickListener {
            if (currentMenuItem.getSizesValues() != null)
                currentMenuItem.increaseCurrSize()
            else
                currentMenuItem.increaseCurrPieces()
            refreshRowData(holder, currentMenuItem)
            //notifyItemChanged(position)
        })
        if (currentMenuItem.getSizesValues() != null) {
            holder.menuItemSwitch.visibility = View.VISIBLE
        } else if (currentMenuItem.getPieces() != null) {
            holder.menuItemSwitch.visibility = View.VISIBLE
        } else {
            holder.menuItemSwitch.visibility = View.GONE
        }
        if (holder.curMenuItem.getFoodType() != FoodType.NORMALE) {
            holder.ll_FoodType.visibility = View.VISIBLE
            holder.iv_FoodType.layoutParams.width = holder.curMenuItem.getFoodType().getIconWidth(FoodType.SizeValues.MEDIUM)
            val icon = holder.curMenuItem.getFoodType().getIconIdx()
            if (icon != null)
                holder.iv_FoodType.setImageResource(icon)
            else
                holder.ll_FoodType.visibility = View.GONE
        } else {
            holder.ll_FoodType.visibility = View.GONE
        }
        setSwitchButtonTxt(holder, currentMenuItem)
    }

    fun getIngredientsString(item: MagnugaMenuItem): String {
        val ingredientsLst = StringBuilder()
        for (i in item.menuItemIngredients()!!)
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
        if (currentMenuItem.getSizesValues() != null) {
            holder.menuItemSwitch.text = currentMenuItem.getCurrentSize()!!.getString(currentMenuItem.menuItemFamily())
        } else if (currentMenuItem.getPieces() != null) {
            holder.menuItemSwitch.text = currentMenuItem.getCurrentPieces()!!.second.toString() + " pezzi"
        } else {
            holder.menuItemSwitch.visibility = View.GONE
        }
    }
}