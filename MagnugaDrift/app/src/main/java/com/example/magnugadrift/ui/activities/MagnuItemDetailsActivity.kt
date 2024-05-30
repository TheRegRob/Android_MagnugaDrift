package com.example.magnugadrift.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.DetailsAdditionsRVAdapter
import com.example.magnugadrift.adapters.DetailsIngredientsRVAdapter
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Order.MagnugaOrderItem


class MagnuItemDetailsActivity : AppCompatActivity(), View.OnClickListener {
    private var favourite: Boolean = true
    private var lst_ingredients: ArrayList<Pair<String, Boolean>> = ArrayList()
    private var lst_additions: ArrayList<AggiuntaType> = ArrayList<AggiuntaType>()
    companion object  {
        var currentPrice: Float = 0.0f
    }
    private lateinit var iv_FoodIcon: ImageView
    private lateinit var iv_FoodImage: ImageView
    private lateinit var tv_Name: TextView
    private lateinit var tv_Ingredients: TextView
    private lateinit var tv_Aggiunte: TextView
    private lateinit var bt_Size: Button
    private lateinit var tv_Family: TextView
    private lateinit var tv_Price: TextView
    private lateinit var tv_Description: TextView
    private lateinit var vd_BottomDivider: View
    private lateinit var tv_finalPrice: TextView
    private lateinit var rv_ingredients: RecyclerView
    private lateinit var rv_additions: RecyclerView
    private lateinit var ib_addAddition: ImageButton
    private lateinit var et_Notes: EditText
    private lateinit var ll_group: LinearLayout
    private lateinit var llc_ingredients: LinearLayout
    private lateinit var llc_additions: LinearLayout
    private lateinit var ll_DescriptionLayout: LinearLayout
    private lateinit var ingredientsAdapter: DetailsIngredientsRVAdapter
    private lateinit var additionAdapter: DetailsAdditionsRVAdapter
    private lateinit var orderItem: MagnugaOrderItem
    private lateinit var iv_FoodType: ImageView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPrice = 0.0f
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_item_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.activity_item_details)
        initView()
        setValuesToViews()
        ingredientsAdapter = DetailsIngredientsRVAdapter(lst_ingredients)
        additionAdapter = DetailsAdditionsRVAdapter(lst_additions,
            orderItem.getOrderItemSize(), tv_finalPrice)
        rv_ingredients.layoutManager = LinearLayoutManager(applicationContext)
        rv_additions.layoutManager = LinearLayoutManager(applicationContext)
        rv_ingredients.adapter = ingredientsAdapter
        rv_additions.adapter = additionAdapter

        ib_addAddition.setOnClickListener{ onClick(ib_addAddition) }
        bt_Size.setOnClickListener{ onClick(bt_Size) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_details_supportactionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav_unfav-> {
                favourite = !favourite
                if (favourite) {
                    item.setIcon(R.drawable.ic_favourite_on)
                } else {
                    item.setIcon(R.drawable.ic_favourite_off)
                }
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        iv_FoodIcon = findViewById(R.id.ActivityItemDetails_iv_FoodIcon)
        iv_FoodImage = findViewById(R.id.ActivityItemDetails_iv_FoodImage)
        tv_Name = findViewById(R.id.tv_food_name)
        tv_Ingredients = findViewById(R.id.tv_ingredients_header)
        tv_Aggiunte = findViewById(R.id.tv_additions_header)
        bt_Size = findViewById(R.id.bt_food_size)
        tv_Price = findViewById(R.id.tb_food_price)
        tv_Description = findViewById(R.id.tv_FoodDescription)
        vd_BottomDivider = findViewById(R.id.vd_BottomDivider)
        tv_Family = findViewById(R.id.tv_food_family)
        tv_finalPrice = findViewById(R.id.tv_finalPrice)
        rv_ingredients = findViewById(R.id.lv_ingredients)
        rv_additions = findViewById(R.id.lv_additions)
        ib_addAddition = findViewById(R.id.ib_add_addition)
        llc_ingredients = findViewById(R.id.lvcustom_ingredients)
        llc_additions = findViewById(R.id.lvcustom_additions)
        ll_DescriptionLayout = findViewById(R.id.ll_DescriptionLayout)
        ll_group = findViewById(R.id.ll_list_group)
        et_Notes = findViewById(R.id.CustviewItemDetailsNotes_et_Notes)
        iv_FoodType = findViewById(R.id.CustviewItemDetailsInfo_iv_FoodType)
        tv_Ingredients.text = "Ingredienti"
        tv_Aggiunte.text = "Aggiunte"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setValuesToViews() {
        orderItem = intent.getSerializableExtra("order_item") as MagnugaOrderItem
        iv_FoodIcon.setImageResource(orderItem.getOrderItemFoodImage())
        if (orderItem.getOrderItemFamily().getThumbnailIdx() != null) {
            iv_FoodImage.setImageResource(orderItem.getOrderItemFamily().getThumbnailIdx()!!)
        } else {
            iv_FoodImage.setImageResource(android.R.color.transparent)
        }
        tv_Name.text = orderItem.getOrderItemName()
        val sizeVal = orderItem.getOrderItemSize()
        val piecesVal = orderItem.getOrderItemPieces()
        if (orderItem.getOrderItemType() != FoodType.NORMALE) {
            iv_FoodType.visibility = View.VISIBLE
            iv_FoodType.layoutParams.width = orderItem.getOrderItemType().getIconWidth(FoodType.SizeValues.SMALL)
            val icon = orderItem.getOrderItemType().getIconIdx()
            if (icon != null) {
                iv_FoodType.setImageResource(icon)
                TooltipCompat.setTooltipText(iv_FoodType, orderItem.getOrderItemType().getTooltipText())
                iv_FoodType.setOnClickListener{ onClick(iv_FoodType) }
            }
            else
                iv_FoodType.visibility = GONE
        } else {
            iv_FoodType.visibility = GONE
        }
        if (sizeVal != null) {
            bt_Size.text = orderItem.magnugaMenuItem.getCurrentSize()!!.getString(orderItem.getOrderItemFamily())
        } else if (piecesVal != null) {
            bt_Size.text = orderItem.magnugaMenuItem.getCurrentPieces()!!.second.toString() + " pezzi"
        } else {
            bt_Size.visibility = GONE
        }
        if (orderItem.magnugaMenuItem.menuItemDescription() != null) {
            tv_Description.text = orderItem.magnugaMenuItem.menuItemDescription()
        } else {
            vd_BottomDivider.visibility = GONE
            ll_DescriptionLayout.visibility = GONE
        }
        et_Notes.setText(orderItem.getOrderItemNote())
        tv_Price.text = String.format("%.2f", orderItem.getOrderItemPrice()) + "€"
        currentPrice += orderItem.getOrderItemPrice()
        tv_Family.text = orderItem.getOrderItemFamily().getString()
        if (orderItem.getOrderItemIngredients() != null) {
            for (food in orderItem.getOrderItemIngredients()!!) {
                lst_ingredients.add(food)
            }
        } else {
            llc_ingredients.visibility = View.GONE
        }
        if (orderItem.getOrderItemAggiunte() != null) {
            for (addition in orderItem.getOrderItemAggiunte()!!) {
                currentPrice += getMainPrice(addition)
                lst_additions.add(addition)
            }
        } else {
            llc_additions.visibility = GONE
        }
        if (llc_ingredients.visibility == GONE &&
            llc_additions.visibility == GONE)
            ll_group.visibility = GONE
        tv_finalPrice.text = String.format("%.2f", currentPrice) + "€"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_add_addition -> {
                setupAdditionDialog()
            }
            R.id.bt_food_size -> {
                if (orderItem.magnugaMenuItem.getSizesValues() != null)
                    orderItem.increaseSize()
                else
                    orderItem.increasePieces()
                refreshOrderValues()
            }
            R.id.CustviewItemDetailsInfo_iv_FoodType -> {
                iv_FoodType.performLongClick()
            }
        }
    }

    fun setupAdditionDialog() {
        val builder = AlertDialog.Builder(this)
        val tmpEnrichLst: ArrayList<AggiuntaType> = ArrayList()
        builder.setTitle("Seleziona l'aggiunta")
        var lst_enrichNames = arrayListOf<String>()
        if (orderItem.getOrderItemEnricheables() != null) {
            for (enrich in orderItem.getOrderItemEnricheables()!!) {
                if (!orderItem.getOrderItemAggiunte()!!.contains(enrich)) {
                    lst_enrichNames.add(enrich.getName())
                    tmpEnrichLst.add(enrich)
                }
            }
        }
        val listItems = lst_enrichNames.toTypedArray<CharSequence>()
        builder.setItems(listItems) {
                dialog, position ->
            orderItem.addToOrderItemAggiunte(tmpEnrichLst[position])
            currentPrice += getMainPrice(tmpEnrichLst[position])
            tv_finalPrice.text = String.format("%.2f", currentPrice) + "€"
            additionAdapter.notifyItemInserted(orderItem.getOrderItemAggiunte()!!.count())
            refreshOrderValues()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun refreshOrderValues() {
        if (orderItem.getOrderItemSize() != null) {
            bt_Size.text =  orderItem.getOrderItemSize()!!.getString(orderItem.getOrderItemFamily())
        } else if (orderItem.getOrderItemPieces() != null) {
            bt_Size.text =  orderItem.getOrderItemPieces()!!.second.toString() + " pezzi"
        }
        tv_Price.text = String.format("%.2f", orderItem.getOrderItemPrice()) + "€"
        currentPrice = orderItem.getOrderItemPrice()
        if (orderItem.getOrderItemAggiunte() != null) {
            additionAdapter = DetailsAdditionsRVAdapter(orderItem.getOrderItemAggiunte()!!,
                orderItem.getOrderItemSize(), tv_finalPrice)
        }
        if (orderItem.getOrderItemAggiunte() != null) {
            rv_additions.adapter = additionAdapter
            additionAdapter.notifyItemInserted(orderItem.getOrderItemAggiunte()!!.count())
        }
        if (orderItem.getOrderItemAggiunte() != null){
            for (addition in orderItem.getOrderItemAggiunte()!!) {
                currentPrice += getMainPrice(addition)
            }
        }
        tv_finalPrice.text = String.format("%.2f", currentPrice) + "€"
    }

    private fun getMainPrice(addition: AggiuntaType): Float {
        var _prices = addition.getPrice()
        if (orderItem.getOrderItemSize() == null) {
            return _prices[0]
        } else {
            return when (orderItem.getOrderItemSize()) {
                FoodSizes.S -> {
                    _prices[0]
                }

                FoodSizes.M -> {
                    _prices[1]
                }

                FoodSizes.L -> {
                    _prices[2]
                }

                else -> {
                    0.0f
                }
            }
        }
    }
}