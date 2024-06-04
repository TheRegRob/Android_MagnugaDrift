package com.example.magnugadrift.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.NewOrderRVAdapter
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.google.android.material.snackbar.Snackbar

class NewOrderActivity: AppCompatActivity(), View.OnClickListener {
    lateinit var rvAdapter: NewOrderRVAdapter
    lateinit var rv_OrderList: RecyclerView
    lateinit var lvcustom_order: LinearLayout
    lateinit var tv_FinalPrice: TextView
    lateinit var ll_Root: LinearLayout
    lateinit var ll_Container: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_new_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.activity_order_build)
        lvcustom_order = findViewById(R.id.lvcustom_order)
        tv_FinalPrice = findViewById(R.id.tv_finalPrice)
        rv_OrderList = findViewById(R.id.rv_OrderList)
        ll_Root = findViewById(R.id.ActivityOrderBuild_ll_Root)
        ll_Container = findViewById(R.id.ActivityOrderBuild_ll_Container)
        rv_OrderList.layoutManager = LinearLayoutManager(this)
        rv_OrderList.setHasFixedSize(true)
        rvAdapter = NewOrderRVAdapter(MainActivity.lstOrder)
        rv_OrderList.adapter = rvAdapter
        enableSwipeDeletion()
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

    override fun onResume() {
        super.onResume()
        if (MainActivity.lstOrder.isNotEmpty()) {
            val ly_Price = findViewById<LinearLayout>(R.id.ly_priceLayout)
            val bt_Save = findViewById<AppCompatButton>(R.id.bt_SaveOrder)
            ll_Root.gravity = Gravity.FILL
            ll_Container.visibility = View.VISIBLE
            tv_FinalPrice.text = String.format("%.2f", calculateOrderPrice()) + "€"

        }
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
    private fun enableSwipeDeletion() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val order = MainActivity.lstOrder[viewHolder.adapterPosition]
                val position = viewHolder.adapterPosition
                MainActivity.lstOrder.removeAt((viewHolder.adapterPosition))
                rvAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                tv_FinalPrice.text = String.format("%.2f", calculateOrderPrice()) + "€"
                Snackbar.make(rv_OrderList, "Rimosso: " + order.getOrderItemName(), Snackbar.LENGTH_LONG)
                    .setAction(
                        "Annulla"
                    ) {
                        MainActivity.lstOrder.add(position, order)
                        rvAdapter.notifyItemInserted(position)
                        tv_FinalPrice.text = String.format("%.2f", calculateOrderPrice()) + "€"
                    }.show()
            }
        }).attachToRecyclerView(rv_OrderList)
    }
    private fun calculateOrderPrice(): Float {
        var finalOrderPrice = 0f
        for (item in MainActivity.lstOrder) {
            finalOrderPrice += item.getFinalPrice()
        }
        return finalOrderPrice
    }
}