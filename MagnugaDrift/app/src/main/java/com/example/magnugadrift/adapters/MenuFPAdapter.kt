package com.example.magnugadrift.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.magnugadrift.classes.Menu.Enums.MenuMode
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory
import com.example.magnugadrift.ui.menu.MenuFragment

class MenuFPAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val menuMode: MenuMode
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return MenuCategory.values().count()
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MenuCategory.CIBO.getValue() -> MenuFragment.newInstance(MenuCategory.CIBO, menuMode)
            MenuCategory.BERE.getValue() -> MenuFragment.newInstance(MenuCategory.BERE, menuMode)
            MenuCategory.DOLCI.getValue() -> MenuFragment.newInstance(MenuCategory.DOLCI, menuMode)
            else -> MenuFragment.newInstance(MenuCategory.CIBO, menuMode)
        }
    }
}