package com.example.magnugadrift.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.classes.Menu.Enums.MenuMode
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.example.magnugadrift.databinding.FragmentMenuBinding
import com.example.magnugadrift.ui.activities.MagnuItemDetailsActivity
import com.example.magnugadrift.ui.activities.MenuItemOrderDetailsActivity

class MenuFragment() : Fragment() {
    companion object {
        fun newInstance(menuCategory: MenuCategory, menuMode: MenuMode) = MenuFragment().apply {
            arguments = Bundle().apply {
                putInt("SELECTED_MENU_TYPE", menuCategory.getValue())
                putInt("SELECTED_MENU_MODE", menuMode.getValue())
            }
        }
    }

    private var _binding: FragmentMenuBinding? = null
    lateinit private var _selectedMenuCategory: MenuCategory
    lateinit private var _selectedMenuMode: MenuMode

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : MenuViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt("SELECTED_MENU_TYPE")?.let {
            _selectedMenuCategory = MenuCategory.fromInt(it)
        }
        arguments?.getInt("SELECTED_MENU_MODE")?.let {
            _selectedMenuMode = MenuMode.fromInt(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val menuList = when (_selectedMenuCategory) {
            MenuCategory.CIBO -> MainActivity.foodMagnuMenu.GetAllMenu()
            MenuCategory.BERE -> MainActivity.drinkMagnuMenu.GetAllDrinks()
            MenuCategory.DOLCI -> MainActivity.dessertMagnuMenu.GetAllDesserts()
        }
        val rvAdapter = MenuRVAdapter(menuList)
        val recyclerView = binding.rvMenuList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = rvAdapter
        val data = ArrayList<MenuRVAdapter>()
        rvAdapter.setRecyclerViewEvent(object : MenuRVAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int) {
                var intent: Intent
                val magnugaOrderItem = MagnugaOrderItem(menuList[position])
                if (_selectedMenuMode == MenuMode.CONSULTATION) {
                    intent = Intent(activity, MagnuItemDetailsActivity::class.java)
                } else {
                    intent = Intent(activity, MenuItemOrderDetailsActivity::class.java)
                }
                intent.putExtra("order_item", magnugaOrderItem)
                startActivity(intent)
            }
        })
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}