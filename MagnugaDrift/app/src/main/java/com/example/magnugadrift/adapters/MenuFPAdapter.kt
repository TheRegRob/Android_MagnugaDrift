package com.example.magnugadrift.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.magnugadrift.classes.Menu.Enums.MenuMode
import com.example.magnugadrift.classes.Menu.Enums.MenuType
import com.example.magnugadrift.ui.menu.MenuFragment

class MenuFPAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val menuMode: MenuMode
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return MenuType.values().count()
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MenuType.CIBO.getValue() -> MenuFragment.newInstance(MenuType.CIBO, menuMode)
            MenuType.BERE.getValue() -> MenuFragment.newInstance(MenuType.BERE, menuMode)
            MenuType.DOLCI.getValue() -> MenuFragment.newInstance(MenuType.DOLCI, menuMode)
            else -> MenuFragment.newInstance(MenuType.CIBO, menuMode)
        }
    }
}