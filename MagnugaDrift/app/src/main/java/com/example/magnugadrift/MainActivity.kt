package com.example.magnugadrift

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.magnugadrift.classes.FoodFamilies
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.MagnugaMenu
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem
import com.example.magnugadrift.classes.Menu.PizzaNapoletanaMI
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.classes.Menu.SpianataMI
import com.example.magnugadrift.classes.Menu.SpianataRipienaMI
import com.example.magnugadrift.classes.UIContent
import com.example.magnugadrift.databinding.ActivityMainBinding
import com.example.magnugadrift.utils.ReadJSONFromAssets
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var magnuMenu: MagnugaMenu
    }


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

    fun createMenu(jsonFile: UIContent) {
        val pizzeNapoletane = ArrayList<MagnugaMenuItem>()
        val pizzeNapoletaneJson = jsonFile.food_list.pizze_napoletane
        val spianate = ArrayList<MagnugaMenuItem>()
        val spianateJson = jsonFile.food_list.spianate
        val spianateRipiene = ArrayList<MagnugaMenuItem>()
        val spianateRipieneJson = jsonFile.food_list.spianate_ripiene

        getPizzeNapoletane(pizzeNapoletaneJson, pizzeNapoletane)
        getSpianate(spianateJson, spianate)
        getSpianateRipiene(spianateRipieneJson, spianateRipiene)

        magnuMenu = MagnugaMenu(pizzeNapoletane, spianate, spianateRipiene)
    }

    fun getPizzeNapoletane(pizzeNapoletane: List<FoodFamilies.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (p in pizzeNapoletane) {
            val nPizza = PizzaNapoletanaMI(
                p.nome, p.prezzo.toTypedArray(),
                FoodType.values()[p.tipo], ArrayList(p.ingredienti), sizesToArray(p.taglie)
            )
            lst.add(nPizza)
        }
    }

    fun getSpianate(spianate: List<FoodFamilies.FoodEntry>, lst: ArrayList<MagnugaMenuItem>) {
        for (s in spianate) {
            val nSpianata = SpianataMI(
                s.nome, s.prezzo.toTypedArray(),
                FoodType.values()[s.tipo], ArrayList(s.ingredienti), sizesToArray(s.taglie)
            )
            lst.add(nSpianata)
        }
    }

    fun getSpianateRipiene(spianateRipiene: List<FoodFamilies.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (s in spianateRipiene) {
            val nSpianata = SpianataRipienaMI(
                s.nome, s.prezzo.toTypedArray(),
                FoodType.values()[s.tipo], ArrayList(s.ingredienti), sizesToArray(s.taglie)
            )
            lst.add(nSpianata)
        }
    }

    fun sizesToArray(taglie: List<Int>): ArrayList<PizzaSizes> {
        val lst = mutableListOf<PizzaSizes>()
        for (t in taglie) {
            lst.add(PizzaSizes.values()[t])
        }
        return ArrayList(lst)
    }
}