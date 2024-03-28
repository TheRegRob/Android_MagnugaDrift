package com.example.magnugadrift.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.MeasureSpec
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.DetailsAdditionsLVAdapter
import com.example.magnugadrift.adapters.DetailsIngredientsLVAdapter
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Order.MagnugaOrderItem


class MagnuItemDetailsActivity() : AppCompatActivity() {
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
    private lateinit var ingredientsAdapter: DetailsIngredientsLVAdapter
    private lateinit var additionAdapter: DetailsAdditionsLVAdapter

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
        additionAdapter = DetailsAdditionsLVAdapter(this, lst_additions)
        lv_ingredients.adapter = ingredientsAdapter
        lv_additions.adapter = additionAdapter
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
        iv_Image = findViewById(R.id.iv_ItemImage)
        tv_Name = findViewById(R.id.tv_food_name)
        tv_Ingredients = findViewById(R.id.tv_ingredients_header)
        tv_Aggiunte = findViewById(R.id.tv_additions_header)
        tv_Size = findViewById(R.id.tv_food_size)
        tv_Price = findViewById(R.id.tb_food_price)
        tv_Family = findViewById(R.id.tv_food_family)
        lv_ingredients = findViewById(R.id.lv_ingredients)
        lv_additions = findViewById(R.id.lv_additions)
        tv_Ingredients.text = "Ingredienti"
        tv_Aggiunte.text = "Aggiunte"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setValuesToViews() {
        var orderItem = intent.getSerializableExtra("order_item") as? MagnugaOrderItem
        if (orderItem != null) {
            //iv_Image.setImageResource(orderItem.getOrderItemFoodImage()) Non setto perché tengo la pizza mo
            tv_Name.text = orderItem.getOrderItemName()
            tv_Size.text = orderItem.getOrderItemSize().toString()
            tv_Price.text = orderItem.getOrderItemPrice().toString()
            tv_Family.text = orderItem.getOrderItemFamily().toString()
            for(food in orderItem.getOrderItemIngredients()!!) {
                lst_ingredients.add(food)
            }
            for (addition in orderItem.getOrderItemAggiunte()!!) {
                var _addition = AggiuntaType(addition)
                lst_additions.add(_addition)
            }
        }
    }
}