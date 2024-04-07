package com.example.magnugadrift.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.magnugadrift.R

class NewOrderActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_new_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)

        setContentView(R.layout.activity_order_build)
        val bt_AddToOrder = findViewById<Button>(R.id.bt_AddToOrder)
        val bt_SaveOrder = findViewById<Button>(R.id.bt_SaveOrder)
        bt_AddToOrder.setOnClickListener{ onClick(bt_AddToOrder) }
        bt_SaveOrder.setOnClickListener{ onClick(bt_SaveOrder) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(v: View) {
        when(v.id) {
            R.id.bt_AddToOrder -> {/*TODO: Complete*/}
            R.id.bt_SaveOrder -> {/*TODO: Complete*/}
        }
    }

}