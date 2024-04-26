package com.example.magnugadrift.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class MenuOrderActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_menu_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.activity_menu_order)
        var menuList = MainActivity.magnuMenu.getAllMenu()
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

        /*val bt_AddToOrder = findViewById<Button>(R.id.bt_AddToOrder)
        val bt_SaveOrder = findViewById<Button>(R.id.bt_SaveOrder)
        bt_AddToOrder.setOnClickListener{ onClick(bt_AddToOrder) }
        bt_SaveOrder.setOnClickListener{ onClick(bt_SaveOrder) }*/
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
        TODO("Not yet implemented")
    }
}