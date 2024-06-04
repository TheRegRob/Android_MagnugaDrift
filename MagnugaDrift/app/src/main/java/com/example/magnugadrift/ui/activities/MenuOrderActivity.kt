package com.example.magnugadrift.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class MenuOrderActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_menu_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.activity_menu_order)
        var menuList = MainActivity.foodMagnuMenu.GetAllMenu()
        val rvAdapter = MenuRVAdapter(menuList)
        val recyclerView = findViewById<RecyclerView>(R.id.rvMenuList_order)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = rvAdapter
        rvAdapter.setRecyclerViewEvent(object : MenuRVAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, MenuItemOrderDetailsActivity::class.java)
                val magnugaOrderItem = MagnugaOrderItem(menuList[position])
                intent.putExtra("order_item", magnugaOrderItem)
                startActivity(intent)
            }
        })
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
}