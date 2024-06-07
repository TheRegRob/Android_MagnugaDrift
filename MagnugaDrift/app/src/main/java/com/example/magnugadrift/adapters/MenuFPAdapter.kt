package com.example.magnugadrift.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.magnugadrift.classes.Menu.Enums.MenuType
import com.example.magnugadrift.ui.menu.MenuFragment

class MenuFPAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return MenuType.values().count()
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MenuType.CIBO.getValue() -> MenuFragment.newInstance(MenuType.CIBO)
            MenuType.BERE.getValue() -> MenuFragment.newInstance(MenuType.BERE)
            MenuType.DOLCI.getValue() -> MenuFragment.newInstance(MenuType.DOLCI)
            else -> MenuFragment.newInstance(MenuType.CIBO)
        }
    }
}