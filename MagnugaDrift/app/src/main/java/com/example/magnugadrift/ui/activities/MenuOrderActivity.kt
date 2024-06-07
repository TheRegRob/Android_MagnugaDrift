package com.example.magnugadrift.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuFPAdapter
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.classes.Menu.Enums.MenuMode
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.google.android.material.tabs.TabLayout

class MenuOrderActivity: AppCompatActivity() {
    private lateinit var tl_TabLayout: TabLayout
    private lateinit var vp_ViewPager: ViewPager2
    private lateinit var adapter: MenuFPAdapter
    private var _selectedMenuMode: MenuMode = MenuMode.ORDER
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.barlayout_menu_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        setContentView(R.layout.tablayout_menu_pager)
        initView()
        /*var menuList = MainActivity.foodMagnuMenu.GetAllMenu()
        rvAdapter.setRecyclerViewEvent(object : MenuRVAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, MenuItemOrderDetailsActivity::class.java)
                val magnugaOrderItem = MagnugaOrderItem(menuList[position])
                intent.putExtra("order_item", magnugaOrderItem)
                startActivity(intent)
            }
        })*/
    }

    fun initView() {
        tl_TabLayout = findViewById(R.id.TablayoutMenuPager_tl_TabLayout)
        vp_ViewPager = findViewById(R.id.TablayoutMenuPager_vp_ViewPager)
        adapter = MenuFPAdapter(supportFragmentManager, lifecycle, _selectedMenuMode)
        tl_TabLayout.addTab(tl_TabLayout.newTab().setText("Cibo"))
        tl_TabLayout.addTab(tl_TabLayout.newTab().setText("Bere"))
        tl_TabLayout.addTab(tl_TabLayout.newTab().setText("Dolci"))
        vp_ViewPager.isSaveFromParentEnabled = false
        vp_ViewPager.adapter = adapter
        vp_ViewPager.offscreenPageLimit = 3
        tl_TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vp_ViewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        vp_ViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tl_TabLayout.selectTab(tl_TabLayout.getTabAt(position))
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