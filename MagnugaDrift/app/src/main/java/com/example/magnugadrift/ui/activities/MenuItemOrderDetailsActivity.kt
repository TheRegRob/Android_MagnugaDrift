package com.example.magnugadrift.ui.activities

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
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.DetailsAdditionsRVAdapter
import com.example.magnugadrift.adapters.DetailsIngredientsRVAdapter
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class MenuItemOrderDetailsActivity  : AppCompatActivity(), View.OnClickListener {
    private var favourite: Boolean = true
    private var lst_ingredients: ArrayList<Pair<String, Boolean>> = ArrayList()
    companion object  {
        var currentPrice: Float = 0.0f
    }
    private lateinit var iv_Image: ImageView
    private lateinit var tv_Name: TextView
    private lateinit var tv_Ingredients: TextView
    private lateinit var tv_Aggiunte: TextView
    private lateinit var bt_Size: Button
    private lateinit var bt_AddToOrder: Button
    private lateinit var tv_Family: TextView
    private lateinit var tv_Price: TextView
    private lateinit var tv_finalPrice: TextView
    private lateinit var rv_ingredients: RecyclerView
    private lateinit var rv_additions: RecyclerView
    private lateinit var ib_addAddition: ImageButton
    private lateinit var rb_StarsReview: RatingBar
    private lateinit var et_Notes: EditText
    private lateinit var ingredientsAdapter: DetailsIngredientsRVAdapter
    private lateinit var additionAdapter: DetailsAdditionsRVAdapter
    private lateinit var orderItem: MagnugaOrderItem

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
        iv_Image = findViewById(R.id.iv_menu_food_icon)
        tv_Name = findViewById(R.id.tv_food_name)
        tv_Ingredients = findViewById(R.id.tv_ingredients_header)
        tv_Aggiunte = findViewById(R.id.tv_additions_header)
        bt_Size = findViewById(R.id.bt_food_size)
        tv_Price = findViewById(R.id.tb_food_price)
        tv_Family = findViewById(R.id.tv_food_family)
        tv_finalPrice = findViewById(R.id.tv_finalPrice)
        rv_ingredients = findViewById(R.id.lv_ingredients)
        rv_additions = findViewById(R.id.lv_additions)
        ib_addAddition = findViewById(R.id.ib_add_addition)
        et_Notes = findViewById(R.id.et_Notes)
        et_Notes.isEnabled = false
        tv_Ingredients.text = "Ingredienti"
        tv_Aggiunte.text = "Aggiunte"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setValuesToViews() {
        orderItem = intent.getSerializableExtra("order_item") as MagnugaOrderItem
        if (orderItem != null) {
            iv_Image.setImageResource(orderItem.getOrderItemFoodImage())
            tv_Name.text = orderItem.getOrderItemName()
            val sizeVal = orderItem.getOrderItemSize()
            if (sizeVal != null) {
                bt_Size.text = sizeVal.toString()
            } else {
                bt_Size.visibility = View.GONE
            }
            et_Notes.setText(orderItem.getOrderItemNote())
            tv_Price.text = String.format("%.2f", orderItem.getOrderItemPrice()) + "€"
            MagnuItemDetailsActivity.currentPrice += orderItem.getOrderItemPrice()
            tv_Family.text = orderItem.getOrderItemFamily().toString()
            for(food in orderItem.getOrderItemIngredients()!!) {
                lst_ingredients.add(food)
            }
            if (orderItem.getOrderItemAggiunte() != null) {
                for (addition in orderItem.getOrderItemAggiunte()!!) {
                    MagnuItemDetailsActivity.currentPrice += getMainPrice(addition)
                    orderItem.addToOrderItemAggiunte(addition)
                }
            } else {
                val lv: LinearLayout = findViewById(R.id.lvcustom_additions)
                lv.visibility = View.GONE
            }
            tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_add_addition -> {
                setupAdditionDialog()
            }
            R.id.bt_food_size -> {
                orderItem.increaseSize()
                refreshOrderValues()
            }
            R.id.btSaveAddDetails -> {
                /* Aggiungere alla lista il piatto selezionato e tornare alla schermata dell'ordine*/
                var nOrder = orderItem
                nOrder.setFinalPrice(MagnuItemDetailsActivity.currentPrice)
                nOrder.getOrderItemAggiunte()
                nOrder.setOrderItemIngredients(lst_ingredients)
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
            MagnuItemDetailsActivity.currentPrice += getMainPrice(tmpEnrichLst[position])
            tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
            additionAdapter.notifyItemInserted(orderItem.getOrderItemAggiunte()!!.count())
            refreshOrderValues()
        }
        val dialog = builder.create()
        dialog.show()
    }
    
    fun refreshOrderValues() {
        bt_Size.text = orderItem.getOrderItemSize().toString()
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
                MagnuItemDetailsActivity.currentPrice += getMainPrice(addition)
            }
        }
        tv_finalPrice.text = String.format("%.2f", MagnuItemDetailsActivity.currentPrice) + "€"
    }

    private fun getMainPrice(addition: AggiuntaType): Float {
        var _prices = addition.getPrice()
        if (orderItem.getOrderItemSize() == null) {
            return _prices[0]
        } else {
            when (orderItem.getOrderItemSize()) {
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
}