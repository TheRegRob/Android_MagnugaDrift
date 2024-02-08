package com.example.magnugadrift.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.magnugadrift.R

class MagnuItemDetailsActivity : AppCompatActivity() {
    private lateinit var iv_Image: ImageView
    private lateinit var tv_Name: TextView
    private lateinit var tv_Ingredients: TextView
    private lateinit var tv_Size: TextView
    private lateinit var tv_Price: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magnu_item_details)

        initView()
        setValuesToViews()
    }

    private fun initView() {
        iv_Image = findViewById(R.id.iv_ItemImage);
        tv_Name = findViewById(R.id.tv_ItemName);
        tv_Ingredients = findViewById(R.id.tv_Ingredients);
        tv_Size = findViewById(R.id.tv_Size);
        tv_Price = findViewById(R.id.tv_Price);
    }

    private fun setValuesToViews() {
        var imgIdx = intent.getStringExtra("itemImage")?.toInt()
        if (imgIdx != null) {
            iv_Image.setImageResource(imgIdx)
        }
        tv_Name.text = intent.getStringExtra("itemName")
        tv_Ingredients.text = intent.getStringExtra("itemIngredients")
        tv_Size.text = intent.getStringExtra("itemSize")
        tv_Price.text = intent.getStringExtra("itemPrice")
    }
}