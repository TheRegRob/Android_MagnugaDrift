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
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.PiecesSizes
import com.example.magnugadrift.classes.Menu.FrittiMI
import com.example.magnugadrift.classes.Menu.SpianataMI
import com.example.magnugadrift.classes.Menu.SpianataRipienaMI
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.example.magnugadrift.classes.UIContent
import com.example.magnugadrift.databinding.ActivityMainBinding
import com.example.magnugadrift.utils.ReadJSONFromAssets
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var magnuMenu: MagnugaMenu
        /* Gestire pulizia lista se esco dall'ordine */
        lateinit var lstOrder: ArrayList<MagnugaOrderItem>
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
        lstOrder = ArrayList()

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
        val fritti = ArrayList<MagnugaMenuItem>()
        val frittiJson = jsonFile.food_list.fritti

        getPizzeNapoletane(pizzeNapoletaneJson, pizzeNapoletane)
        getSpianate(spianateJson, spianate)
        getSpianateRipiene(spianateRipieneJson, spianateRipiene)
        getFritti(frittiJson, fritti)

        magnuMenu = MagnugaMenu(pizzeNapoletane, spianate, spianateRipiene, fritti)
    }

    fun getPizzeNapoletane(pizzeNapoletane: List<FoodFamilies.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (p in pizzeNapoletane) {
            val nPizza = PizzaNapoletanaMI(
                p.nome, p.descrizione, p.prezzo.toTypedArray(),
                FoodType.values()[p.tipo], ingredientsToArray(p.ingredienti), sizesToArray(p.taglie)
            )
            lst.add(nPizza)
        }
    }

    fun getSpianate(spianate: List<FoodFamilies.FoodEntry>, lst: ArrayList<MagnugaMenuItem>) {
        for (s in spianate) {
            val nSpianata = SpianataMI(
                s.nome, s.descrizione, s.prezzo.toTypedArray(),
                FoodType.values()[s.tipo], ingredientsToArray(s.ingredienti), sizesToArray(s.taglie)
            )
            lst.add(nSpianata)
        }
    }

    fun getSpianateRipiene(spianateRipiene: List<FoodFamilies.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (s in spianateRipiene) {
            val nSpianata = SpianataRipienaMI(
                s.nome, s.descrizione, s.prezzo.toTypedArray(),
                FoodType.values()[s.tipo], ingredientsToArray(s.ingredienti), sizesToArray(s.taglie)
            )
            lst.add(nSpianata)
        }
    }

    fun getFritti(fritti: List<FoodFamilies.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (f in fritti) {
            val nFritto = FrittiMI(
                f.nome, f.descrizione, f.prezzo.toTypedArray(),
                FoodType.values()[f.tipo], ingredientsToArray(f.ingredienti), sizesToArray(f.taglie),
                piecesToArray(f.pezzi),
            )
            lst.add(nFritto)
        }
    }

    fun ingredientsToArray(ingredients: List<String>?): ArrayList<String>? {
        return if (ingredients != null) {
            val lst = mutableListOf<String>()
            for (t in ingredients) {
                lst.add(t)
            }
            ArrayList(lst)
        } else {
            null
        }
    }

    fun sizesToArray(taglie: List<Int>?): ArrayList<FoodSizes>? {
        return if (taglie != null) {
            val lst = mutableListOf<FoodSizes>()
            for (t in taglie) {
                lst.add(FoodSizes.values()[t])
            }
            ArrayList(lst)
        } else {
            null
        }
    }

    fun piecesToArray(pieces: List<Int>?): ArrayList<Pair<PiecesSizes, Int>>? {
        return if (pieces != null) {
            val lst = mutableListOf<Pair<PiecesSizes, Int>>()
            if (pieces.isNotEmpty()) {
                for (i in 0 ..< pieces.count()) {
                    val dbg1 = PiecesSizes.values()[i]
                    val dbg2 = pieces[i]
                    lst.add(Pair(PiecesSizes.values()[i], pieces[i]))
                }
            }
                ArrayList(lst)
        } else {
            null
        }
    }
}