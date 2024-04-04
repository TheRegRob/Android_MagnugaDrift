package com.example.magnugadrift.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.ui.activities.MagnuItemDetailsActivity
import org.w3c.dom.Text

class DetailsAdditionsRVAdapter(private val additionsList: ArrayList<AggiuntaType>,
                                private val size: PizzaSizes? = null,
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
            R.layout.lstview_item_additions,
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
        additionName.text = addition.getName()
        additionPrice.text = getMainPrice(addition).toString() + "€"
        bt_delete.setOnClickListener(View.OnClickListener {
            var newPrice: Float = MagnuItemDetailsActivity.currentPrice - getMainPrice(additionsList[position])
            MagnuItemDetailsActivity.currentPrice = newPrice
            additionsList.remove(additionsList[position])
            tv_fPrice.text = String.format("%.2f", newPrice) + "€"
            notifyItemRemoved(position)
        })
    }

    private fun getMainPrice(addition: AggiuntaType): Float {
        var _prices = addition.getPrice()
        if (size == null) {
            return _prices[0]
        } else {
            when (size) {
                PizzaSizes.PICCOLA -> {
                    return _prices[0]
                }
                PizzaSizes.MEDIA -> {
                    return _prices[1]
                }
                PizzaSizes.MAXI -> {
                    return _prices[2]
                }
                else -> {
                    return 0.0f
                }
            }
        }
    }

    /*override fun getCount(): Int {
        return additionsList.size
    }

    override fun getItem(p0: Int): Any {
        return additionsList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    private fun getMainPrice(addition: AggiuntaType): Float {
        var _prices = addition.getPrice()
        if (size == null) {
            return _prices[0]
        } else {
            when (size) {
                PizzaSizes.PICCOLA -> {
                    return _prices[0]
                }
                PizzaSizes.MEDIA -> {
                    return _prices[1]
                }
                PizzaSizes.MAXI -> {
                    return _prices[2]
                }
                else -> {
                    return 0.0f
                }
            }
        }
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?: LayoutInflater.from(ctx).inflate(R.layout.lstview_item_additions, p2, false)
        val additionName = view.findViewById<TextView>(R.id.tv_additionName)
        val additionPrice = view.findViewById<TextView>(R.id.tv_additionPrice)
        val bt_delete = view.findViewById<ImageButton>(R.id.bt_delete_addition)
        val addition = additionsList[p0]
        additionName.text = addition.getName()
        additionPrice.text = getMainPrice(addition).toString() + "€"
        bt_delete.setOnClickListener(View.OnClickListener {
            var newPrice: Float = MagnuItemDetailsActivity.currentPrice - getMainPrice(additionsList[p0])
            MagnuItemDetailsActivity.currentPrice = newPrice
            this.additionsList.remove(additionsList[p0])
            tv_fPrice.text = String.format("%.2f", newPrice) + "€"
            notifyDataSetChanged()
        })
        return view
    }*/
}