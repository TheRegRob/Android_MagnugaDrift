package com.example.magnugadrift

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.magnugadrift.classes.FoodType
import com.example.magnugadrift.classes.Menu.MagnugaMenu
import com.example.magnugadrift.classes.Menu.PizzaNapoletanaMI
import com.example.magnugadrift.classes.PizzaSizes
import com.example.magnugadrift.classes.UIContent
import com.example.magnugadrift.databinding.ActivityMainBinding
import com.example.magnugadrift.ui.menu.MenuFragment
import com.example.magnugadrift.utils.ReadJSONFromAssets
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var magnuMenu: MagnugaMenu

    private lateinit var binding: ActivityMainBinding
    lateinit var data: UIContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonStr = loadMenuEntries()
        data = Gson().fromJson(jsonStr, UIContent::class.java)
        createMenu(data)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_order, R.id.navigation_menu, R.id.navigation_history
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun loadMenuEntries() : String {
        return ReadJSONFromAssets(baseContext, "magnuga_menu.json")
    }

    fun getJsonData(): UIContent {
        return data
    }
    fun getMenuList(): MagnugaMenu {
        return magnuMenu
    }

    fun createMenu(jsonFile: UIContent) {
        val pizzeNapoletane = ArrayList<PizzaNapoletanaMI>()
        val pizzeNapoletaneJson = jsonFile.food_list.pizze_napoletane
        for (p in pizzeNapoletaneJson) {
            val nPizza = PizzaNapoletanaMI(0, p.nome, p.prezzo.toTypedArray(),
                FoodType.values()[p.tipo], p.ingredienti, sizesToArray(p.taglie))
            pizzeNapoletane.add(nPizza)
        }

        magnuMenu = MagnugaMenu(pizzeNapoletane)
    }

    fun sizesToArray(taglie: List<Int>): Array<PizzaSizes> {
        val lst = mutableListOf<PizzaSizes>()
        for (t in taglie) {
            lst.add(PizzaSizes.values()[t])
        }
        return lst.toTypedArray()
    }
}