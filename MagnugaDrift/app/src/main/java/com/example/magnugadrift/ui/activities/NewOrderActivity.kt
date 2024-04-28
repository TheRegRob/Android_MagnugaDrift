package com.example.magnugadrift.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.adapters.NewOrderRVAdapter
import com.example.magnugadrift.classes.Menu.MagnugaMenu
import com.example.magnugadrift.classes.Order.MagnugaOrderItem

class NewOrderActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_new_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.activity_order_build)
        val rvAdapter = NewOrderRVAdapter(MainActivity.lstOrder)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_OrderList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = rvAdapter
        rvAdapter.setRecyclerViewEvent(object : NewOrderRVAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, MenuItemOrderDetailsActivity::class.java)
                val magnugaOrderItem = MagnugaOrderItem(MainActivity.lstOrder[position].magnugaMenuItem)
                //intent.putExtra("order_item", magnugaOrderItem)
                //startActivity(intent)
            }
        })
        val bt_AddNewToOrder = findViewById<Button>(R.id.bt_AddNewToOrder)
        val bt_SaveOrder = findViewById<Button>(R.id.bt_SaveOrder)
        bt_AddNewToOrder.setOnClickListener{ onClick(bt_AddNewToOrder) }
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
            R.id.bt_AddNewToOrder -> {
                val intent = Intent(this, MenuOrderActivity::class.java)
                startActivity(intent)
            }
            R.id.bt_SaveOrder -> {/*TODO: Complete*/}
        }
    }

}