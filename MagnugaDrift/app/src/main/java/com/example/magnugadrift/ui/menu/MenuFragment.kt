package com.example.magnugadrift.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magnugadrift.MainActivity
import com.example.magnugadrift.R
import com.example.magnugadrift.adapters.MenuRVAdapter
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem
import com.example.magnugadrift.classes.Menu.PizzaNapoletanaMI
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.example.magnugadrift.classes.UIContent
import com.example.magnugadrift.databinding.MenuFragmentBinding
import com.example.magnugadrift.ui.activities.MagnuItemDetailsActivity
import java.io.Serializable

class MenuFragment : Fragment() {

    private var _binding: MenuFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MenuViewModel::class.java)

        _binding = MenuFragmentBinding.inflate(inflater, container, false)
        //var view: View = inflater.inflate(R.layout.activity_magnu_item_details, container, false)
        var menuList = (activity as MainActivity).getMenuList().getAllMenu()
        val rvAdapter = MenuRVAdapter(menuList)
        val recyclerView = binding.rvMenuList
        val dd = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(activity as MainActivity, R.drawable.divider)
            ?.let { dd.setDrawable(it) }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = rvAdapter
        //recyclerView.addItemDecoration(dd)
        val data = ArrayList<MenuRVAdapter>()
        rvAdapter.setRecyclerViewEvent(object : MenuRVAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int) {
                val intent = Intent(activity, MagnuItemDetailsActivity::class.java)

                val magnugaOrderItem = MagnugaOrderItem(menuList[position])

                intent.putExtra("order_item", magnugaOrderItem)

                /*intent.putExtra("itemImage", menuList[position].getResourceImage().toString())
                intent.putExtra("itemName", menuList[position].menuItemName())
                intent.putExtra("itemIngredients", getIngredientsString(menuList[position]))
                intent.putExtra("itemSize", menuList[position].getCurrentSize().toString())
                intent.putExtra("itemFamily", menuList[position].menuItemFamily().toString())
                intent.putExtra("itemPrice", menuList[position].getCurrentPrice().toString() + "€")*/

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

    fun getIngredientsString(item: MagnugaMenuItem): String {
        val ingredientsLst = StringBuilder()
        for (i in item.menuItemIngredients())
            ingredientsLst.append(i + ", ")
        var ingredients = ingredientsLst.toString()
        if (ingredients != "") {
            ingredients = ingredients.substring(0, ingredients.length - 2)
        }
        return ingredients
    }
}