package com.example.magnugadrift.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.DetailsAdditionsLVAdapter
import com.example.magnugadrift.adapters.DetailsIngredientsLVAdapter
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Order.MagnugaOrderItem


class MagnuItemDetailsActivity() : AppCompatActivity(), View.OnClickListener {
    private var favourite: Boolean = true
    private var lst_ingredients: ArrayList<String> = ArrayList<String>()
    private var lst_additions: ArrayList<AggiuntaType> = ArrayList<AggiuntaType>()
    private lateinit var iv_Image: ImageView
    private lateinit var tv_Name: TextView
    private lateinit var tv_Ingredients: TextView
    private lateinit var tv_Aggiunte: TextView
    private lateinit var tv_Size: TextView
    private lateinit var tv_Family: TextView
    private lateinit var tv_Price: TextView
    private lateinit var tv_Note: TextView
    private lateinit var lv_ingredients: ListView
    private lateinit var lv_additions: ListView
    private lateinit var ib_addAddition: ImageButton
    private lateinit var ingredientsAdapter: DetailsIngredientsLVAdapter
    private lateinit var additionAdapter: DetailsAdditionsLVAdapter
    private lateinit var orderItem: MagnugaOrderItem


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.item_details_barlayout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.item_details_activity)
        initView()
        setValuesToViews()
        ingredientsAdapter = DetailsIngredientsLVAdapter(this, lst_ingredients)
        additionAdapter = DetailsAdditionsLVAdapter(this, lst_additions, orderItem.getOrderItemSize())
        lv_ingredients.adapter = ingredientsAdapter
        lv_additions.adapter = additionAdapter
        ib_addAddition.setOnClickListener{ onClick(ib_addAddition) }
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
        iv_Image = findViewById(R.id.iv_menu_food_icon)
        tv_Name = findViewById(R.id.tv_food_name)
        tv_Ingredients = findViewById(R.id.tv_ingredients_header)
        tv_Aggiunte = findViewById(R.id.tv_additions_header)
        tv_Size = findViewById(R.id.tv_food_size)
        tv_Price = findViewById(R.id.tb_food_price)
        tv_Family = findViewById(R.id.tv_food_family)
        lv_ingredients = findViewById(R.id.lv_ingredients)
        lv_additions = findViewById(R.id.lv_additions)
        ib_addAddition = findViewById(R.id.ib_add_addition)
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
                tv_Size.text = sizeVal.toString()
            } else {
                tv_Size.visibility = GONE
            }
            tv_Price.text = orderItem.getOrderItemPrice().toString()
            tv_Family.text = orderItem.getOrderItemFamily().toString()
            for(food in orderItem.getOrderItemIngredients()!!) {
                lst_ingredients.add(food)
            }
            if (orderItem.getOrderItemAggiunte() != null) {
                for (addition in orderItem.getOrderItemAggiunte()!!) {
                    var _addition = AggiuntaType(addition)
                    lst_additions.add(_addition)
                }
            } else {
                val lv: LinearLayout = findViewById(R.id.lvcustom_additions)
                lv.visibility = View.GONE
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_add_addition -> {
                setupAdditionDialog()
            }
        }
    }

    fun setupAdditionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleziona l'aggiunta")
        var lst_enrichNames = arrayListOf<String>()
        if (orderItem.getOrderItemEnricheables() != null) {
            for (enrich in orderItem.getOrderItemEnricheables()!!) {
                lst_enrichNames.add(enrich.getName())
            }
        }
        val listItems = lst_enrichNames.toTypedArray<CharSequence>()
        builder.setItems(listItems) {
            dialog, position ->
                lst_additions.add(orderItem.getOrderItemEnricheables()!![position])
                additionAdapter.notifyDataSetChanged()
            }
        val dialog = builder.create()
        dialog.show()
    }
}