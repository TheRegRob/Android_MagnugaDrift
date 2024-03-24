package com.example.magnugadrift.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class MagnuItemDetailsActivity(magnu_item: MagnugaOrderItem) : AppCompatActivity() {
    private var favourite: Boolean = true
    private var lst_ingredients: ArrayList<String> = ArrayList<String>()
    private var lst_additions: ArrayList<String> = ArrayList<String>()
    private lateinit var iv_Image: ImageView
    private lateinit var tv_Name: TextView
    private lateinit var tv_Ingredients: TextView
    private lateinit var tv_Aggiunte: TextView
    private lateinit var tv_Size: TextView
    private lateinit var tv_Family: TextView
    private lateinit var tv_Price: TextView
    private lateinit var tv_Note: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.item_details_barlayout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.item_details_activity)
        initView()
        setValuesToViews()
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

    /*public fun addItems(view: View) {
        for (ing in )
    }*/

    private fun initView() {
        iv_Image = findViewById(R.id.iv_ItemImage)
        tv_Name = findViewById(R.id.tv_food_name)
        tv_Ingredients = findViewById(R.id.tv_ingredients_header)
        tv_Aggiunte = findViewById(R.id.tv_additions_header)
        tv_Size = findViewById(R.id.tv_food_size)
        tv_Price = findViewById(R.id.tb_food_price)
        tv_Note = findViewById(R.id.tv_Note)
        tv_Family = findViewById(R.id.tv_food_family)
        tv_Ingredients.text = "Ingredienti"
        tv_Aggiunte.text = "Aggiunte"
        tv_Note.text = "Note"
    }

    private fun setValuesToViews() {
        gfdhsgds
        /*TODO: PROVA A CHIAMARE LA VIEW CON IL MENUORDERITEM INVECE DI USARE L'INTENT*/
        var imgIdx = intent.getStringExtra("itemImage")?.toInt()
        if (imgIdx != null) {
            //iv_Image.setImageResource(imgIdx)
        }
        tv_Name.text = intent.getStringExtra("itemName")
        //tv_Ingredients.text = intent.getStringExtra("itemIngredients")
        tv_Size.text = intent.getStringExtra("itemSize")
        tv_Price.text = intent.getStringExtra("itemPrice")
        tv_Family.text = intent.getStringExtra("itemFamily")
    }
}