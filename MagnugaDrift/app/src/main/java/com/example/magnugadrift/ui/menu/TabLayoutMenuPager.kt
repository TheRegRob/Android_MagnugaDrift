package com.example.magnugadrift.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.loader.app.LoaderManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuFPAdapter
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.example.magnugadrift.databinding.FragmentMenuBinding
import com.example.magnugadrift.databinding.TablayoutMenuPagerBinding
import com.example.magnugadrift.ui.activities.MagnuItemDetailsActivity
import com.google.android.material.tabs.TabLayout

class TabLayoutMenuPager: Fragment() {
    private var _binding: TablayoutMenuPagerBinding? = null
    private lateinit var tl_TabLayout: TabLayout
    private lateinit var vp_ViewPager: ViewPager2
    private lateinit var adapter: MenuFPAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TablayoutMenuPagerBinding.inflate(inflater, container, false)

        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    fun initView(view: View) {
        tl_TabLayout = view.findViewById(R.id.FragmentMenu_tl_TabLayout)
        vp_ViewPager = view.findViewById(R.id.FragmentMenu_vp_ViewPager)
        adapter = MenuFPAdapter(childFragmentManager, lifecycle)

        tl_TabLayout.addTab(tl_TabLayout.newTab().setText("Cibo"))
        tl_TabLayout.addTab(tl_TabLayout.newTab().setText("Bere"))
        tl_TabLayout.addTab(tl_TabLayout.newTab().setText("Dolci"))
        vp_ViewPager.isSaveFromParentEnabled = false
        vp_ViewPager.adapter = adapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}