package com.example.magnugadrift.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.DetailsAdditionsRVAdapter
import com.example.magnugadrift.adapters.DetailsIngredientsRVAdapter
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class MenuItemOrderDetailsActivity  : AppCompatActivity(), View.OnClickListener {
    private var favourite: Boolean = true
    private var lst_ingredients: ArrayList<Pair<String, Boolean>> = ArrayList()
    companion object  {
        var currentPrice: Float = 0.0f
    }
    private lateinit var iv_FoodIcon: ImageView
    private lateinit var iv_FoodImage: ImageView
    private lateinit var tv_Name: TextView
    private lateinit var tv_Ingredients: TextView
    private lateinit var tv_Aggiunte: TextView
    private lateinit var bt_Size: Button
    private lateinit var bt_AddToOrder: Button
    private lateinit var tv_Family: TextView
    private lateinit var tv_Price: TextView
    private lateinit var tv_Description: TextView
    private lateinit var vd_BottomDivider: View
    private lateinit var tv_finalPrice: TextView
    private lateinit var rv_ingredients: RecyclerView
    private lateinit var rv_additions: RecyclerView
    private lateinit var ib_addAddition: ImageButton
    private lateinit var rb_StarsReview: RatingBar
    private lateinit var et_Notes: EditText
    private lateinit var ll_group: LinearLayout
    private lateinit var llc_ingredients: LinearLayout
    private lateinit var llc_additions: LinearLayout
    private lateinit var ll_DescriptionLayout: LinearLayout
    private lateinit var ll_Notes: LinearLayout
    private lateinit var ingredientsAdapter: DetailsIngredientsRVAdapter
    private lateinit var additionAdapter: DetailsAdditionsRVAdapter
    private lateinit var orderItem: MagnugaOrderItem
    private lateinit var iv_FoodType: ImageView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MagnuItemDetailsActivity.currentPrice = 0.0f
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_item_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.activity_item_details)
        initView()
        setValuesToViews()
        ingredientsAdapter = DetailsIngredientsRVAdapter(lst_ingredients)
        if (orderItem.getOrderItemAggiunte() != null) {
            additionAdapter = DetailsAdditionsRVAdapter(
                orderItem.getOrderItemAggiunte()!!,
                orderItem.getOrderItemSize(), tv_finalPrice)
            rv_additions.adapter = additionAdapter
        }
        rv_ingredients.layoutManager = LinearLayoutManager(applicationContext)
        rv_additions.layoutManager = LinearLayoutManager(applicationContext)
        rv_ingredients.adapter = ingredientsAdapter

        ib_addAddition.setOnClickListener{ onClick(ib_addAddition) }
        bt_Size.setOnClickListener{ onClick(bt_Size) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_details_supportactionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)  {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        bt_AddToOrder = findViewById(R.id.btSaveAddDetails)
        bt_AddToOrder.text = "AGGIUNGI"
        bt_AddToOrder.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_to_order, 0, 0, 0)
        bt_AddToOrder.setOnClickListener{ onClick(bt_AddToOrder) }
        rb_StarsReview = findViewById(R.id.ratingBar)
        rb_StarsReview.isEnabled = false
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
        ll_Notes = findViewById(R.id.ActivityItemDetails_tv_customNotes)
        iv_FoodType = findViewById(R.id.CustviewItemDetailsInfo_iv_FoodType)
        et_Notes.isEnabled = false
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
                iv_FoodType.visibility = View.GONE
        } else {
            iv_FoodType.visibility = View.GONE
        }
        if (orderItem.getOrderItemRating() == 0) {
            rb_StarsReview.visibility = View.GONE
        }
        if (orderItem.getOrderItemNote().isEmpty()) {
            ll_Notes.visibility = View.GONE
        }
        if (sizeVal != null) {
            bt_Size.text = orderItem.magnugaMenuItem.getCurrentSize()!!.getString(orderItem.getOrderItemFamily())
        } else if (piecesVal != null) {
            bt_Size.text = orderItem.magnugaMenuItem.getCurrentPieces()!!.second.toString() + " pezzi"
        } else {
            bt_Size.visibility = View.GONE
        }
        if (orderItem.magnugaMenuItem.menuItemDescription() != null) {
            tv_Description.text = orderItem.magnugaMenuItem.menuItemDescription()
        } else {
            vd_BottomDivider.visibility = View.GONE
            ll_DescriptionLayout.visibility = View.GONE
        }
        et_Notes.setText(orderItem.getOrderItemNote())
        tv_Price.text = String.format("%.2f", orderItem.getOrderItemPrice()) + "€"
        MagnuItemDetailsActivity.currentPrice += orderItem.getOrderItemPrice()
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
                MagnuItemDetailsActivity.currentPrice += getMainPrice(addition.first)
                orderItem.addToOrderItemAggiunte(addition)
            }
        } else {
            llc_additions.visibility = View.GONE
        }
        if (llc_ingredients.visibility == View.GONE &&
            llc_additions.visibility == View.GONE)
                ll_group.visibility = View.GONE
        tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
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
            R.id.btSaveAddDetails -> {
                /* Aggiungere alla lista il piatto selezionato e tornare alla schermata dell'ordine*/
                var nOrder = orderItem
                nOrder.setFinalPrice(MagnuItemDetailsActivity.currentPrice)
                nOrder.getOrderItemAggiunte()
                if (orderItem.getOrderItemIngredients() != null) {
                    nOrder.setOrderItemIngredients(lst_ingredients)
                } else {
                    nOrder.setOrderItemIngredients(null)
                }
                MainActivity.lstOrder.add(nOrder)
                val intent = Intent(this, NewOrderActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
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
                val enrichPair = Pair(enrich, null)
                if ((enrichPair.first.isRecallable()) ||
                    (!orderItem.getOrderItemAggiunte()!!.contains(enrichPair))) {
                    lst_enrichNames.add(enrich.getName())
                    tmpEnrichLst.add(enrich)
                }
            }
        }
        val listItems = lst_enrichNames.toTypedArray<CharSequence>()
        builder.setItems(listItems) {
                dialog, position ->
            if (tmpEnrichLst[position].isRecallable()) {
                var editTextField: EditText = EditText(this)
                val builderAdditionName = AlertDialog.Builder(this)
                builderAdditionName.setTitle("Digita il nome dell'ingrediente")
                builderAdditionName.setView(editTextField)
                    .setPositiveButton("OK") { _, _ ->
                        val editTextInput = editTextField.text.toString()
                        if (editTextInput.isNotEmpty()) {
                            orderItem.addToOrderItemAggiunte(Pair(tmpEnrichLst[position],
                                editTextInput.replaceFirstChar { firstChar -> firstChar.uppercase() }))
                            MagnuItemDetailsActivity.currentPrice += getMainPrice(tmpEnrichLst[position])
                            tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
                            additionAdapter.notifyItemInserted(orderItem.getOrderItemAggiunte()!!.count())
                            refreshOrderValues()
                        } else {
                            Toast.makeText(this,
                                "Il campo non può essere vuoto",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("Cancel", null)
                val dialogAddition = builderAdditionName.create()
                dialogAddition.show()
            } else {
                orderItem.addToOrderItemAggiunte(Pair(tmpEnrichLst[position], null))
                MagnuItemDetailsActivity.currentPrice += getMainPrice(tmpEnrichLst[position])
                tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
                additionAdapter.notifyItemInserted(orderItem.getOrderItemAggiunte()!!.count())
                refreshOrderValues()
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun refreshOrderValues() {
        if (orderItem.getOrderItemSize() != null) {
            bt_Size.text =  orderItem.getOrderItemSize()!!.getString(orderItem.getOrderItemFamily())
        } else if (orderItem.getOrderItemPieces() != null) {
            bt_Size.text =  orderItem.getOrderItemPieces()!!.second.toString() + " pezzi"
        }
        tv_Price.text = String.format("%.2f", orderItem.getOrderItemPrice()) + "€"
        MagnuItemDetailsActivity.currentPrice = orderItem.getOrderItemPrice()
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
                MagnuItemDetailsActivity.currentPrice += getMainPrice(addition.first)
            }
        }
        tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
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