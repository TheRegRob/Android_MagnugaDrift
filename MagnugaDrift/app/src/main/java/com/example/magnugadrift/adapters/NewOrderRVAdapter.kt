package com.example.magnugadrift.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory
import com.example.magnugadrift.classes.Menu.Enums.MenuItemFamilies
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
        val orderItemImage : ImageView = itemView.findViewById(R.id.RecyclerviewItemOrder_iv_ItemImage)
        val orderItemName : TextView = itemView.findViewById(R.id.RecyclerviewItemOrder_tv_ItemName)
        val orderItemIngredienti: TextView = itemView.findViewById(R.id.RecyclerviewItemOrder_tv_ItemIngredients)
        val orderItemAggiunte: TextView = itemView.findViewById(R.id.RecyclerviewItemOrder_tv_ItemAdditions)
        val orderItemSize: TextView = itemView.findViewById(R.id.RecyclerviewItemOrder_tv_ItemSize)
        val orderItemPrice : TextView = itemView.findViewById(R.id.RecyclerviewItemOrder_tv_ItemPrice)
        val vd_TitleDivider: View = itemView.findViewById(R.id.RecyclerviewItemOrder_vd_TitleDivider)
        val vd_IngregientsDivider: View = itemView.findViewById(R.id.RecyclerviewItemOrder_vd_IngregientsDivider)
        val iv_FoodType: ImageView = itemView.findViewById(R.id.RecyclerviewItemOrder_iv_ItemType)
        val ll_FoodType: LinearLayout = itemView.findViewById(R.id.RecyclerviewItemOrder_ll_ItemType)
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
        holder.orderItemName.text = HtmlCompat.fromHtml(generateFoodNameString(currentOrderItem), HtmlCompat.FROM_HTML_MODE_LEGACY)
        holder.orderItemIngredienti.text = HtmlCompat.fromHtml(generateIngredientsString(holder, currentOrderItem), HtmlCompat.FROM_HTML_MODE_LEGACY)
        holder.orderItemPrice.text = String.format("%.2f", currentOrderItem.getFinalPrice()) + "€"
        holder.orderItemAggiunte.text = HtmlCompat.fromHtml(generateAdditionsString(holder, currentOrderItem), HtmlCompat.FROM_HTML_MODE_LEGACY)
        if (holder.curOrderItem.getOrderItemType() != FoodType.NORMALE) {
            holder.ll_FoodType.visibility = View.VISIBLE
            holder.iv_FoodType.layoutParams.width = holder.curOrderItem.getOrderItemType().getIconWidth(FoodType.SizeValues.MEDIUM)
            val icon = holder.curOrderItem.getOrderItemType().getIconIdx()
            if (icon != null)
                holder.iv_FoodType.setImageResource(icon)
            else
                holder.ll_FoodType.visibility = View.GONE
        } else {
            holder.ll_FoodType.visibility = View.GONE
        }
        setLabelTxt(holder, currentOrderItem)
    }

    fun generateFoodNameString(item: MagnugaOrderItem): String {
        val foodName = StringBuilder()
        when(item.magnugaMenuItem.getCategory()) {
            MenuCategory.CIBO -> foodName.append("<b>Piatto:</b><br>")
            MenuCategory.BERE -> foodName.append("<b>Bevanda:</b><br>")
            MenuCategory.DOLCI -> foodName.append("<b>Dolce:</b><br>")
        }

        foodName.append(item.magnugaMenuItem.menuItemName())
        return foodName.toString()
    }

    fun generateIngredientsString(holder: NewOrderRVAdapter.NewOrderViewHolder, item: MagnugaOrderItem): String {
        val ingredientsLst = StringBuilder()
        if (item.getOrderItemIngredients() != null) {
            if (checkNoIngredients(item)) {
                ingredientsLst.append("<b><i>Nessun ingrediente</i></b>")
            } else {
                ingredientsLst.append("<b>Ingredienti:</b><br>")
                for (i in item.getOrderItemIngredients()!!)
                    if (i.second)
                        ingredientsLst.append(i.first + "<br>")
                    else
                        ingredientsLst.append("<s> <font color='red'>" + i.first + "</font></s><br>")

            }
        } else {
            holder.vd_TitleDivider.visibility = View.GONE
            holder.orderItemIngredienti.visibility = View.GONE
            return ""
        }
        return ingredientsLst.toString()
    }

    fun generateAdditionsString(holder: NewOrderRVAdapter.NewOrderViewHolder, item: MagnugaOrderItem): String {
        val additionsLst = StringBuilder()
        return if (item.getOrderItemAggiunte() != null) {
            if (item.getOrderItemAggiunte()!!.isNotEmpty()) {
                additionsLst.append("<b>Aggiunte:</b><br>")
                for (i in item.getOrderItemAggiunte()!!) {
                    if (i.second == null) {
                        additionsLst.append(i.first.getName() + "<br>")
                    } else {
                        additionsLst.append("<font color='blue'>" + i.second+ "</font><br>")
                    }

                }
            } else {
                additionsLst.append("<b><i>Nessuna aggiunta</i></b>")
            }
            additionsLst.toString()
        } else {
            holder.vd_IngregientsDivider.visibility = View.GONE
            holder.orderItemAggiunte.visibility = View.GONE
            ""
        }
    }
    fun setLabelTxt(holder: NewOrderRVAdapter.NewOrderViewHolder, currentOrderItem: MagnugaOrderItem) {
        if (currentOrderItem.magnugaMenuItem.getSizesValues() != null) {
            holder.orderItemSize.visibility = View.VISIBLE
            holder.orderItemSize.text = currentOrderItem.getOrderItemSize()!!.getString(currentOrderItem.getOrderItemFamily())
        } else if (currentOrderItem.magnugaMenuItem.getPieces() != null) {
            holder.orderItemSize.visibility = View.VISIBLE
            holder.orderItemSize.text = currentOrderItem.getOrderItemPieces()!!.second.toString() + " pezzi"
        } else {
            holder.orderItemSize.visibility = View.GONE
        }
    }
    fun checkNoIngredients(item: MagnugaOrderItem): Boolean {
        var ret = true
        for (i in item.getOrderItemIngredients()!!)
            if (i.second) {
                ret = false
                break
            }
        return ret
    }
}