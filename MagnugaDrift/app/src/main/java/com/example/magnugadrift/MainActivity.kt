package com.example.magnugadrift

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.magnugadrift.classes.DessertFamiliesContent
import com.example.magnugadrift.classes.DrinkFamiliesContent
import com.example.magnugadrift.classes.FoodFamiliesContent
import com.example.magnugadrift.classes.Menu.DessertMagnugaMenu
import com.example.magnugadrift.classes.Menu.Desserts.AltriDolciMI
import com.example.magnugadrift.classes.Menu.Desserts.DonutsMI
import com.example.magnugadrift.classes.Menu.Desserts.FetteTortaMI
import com.example.magnugadrift.classes.Menu.DrinkMagnugaMenu
import com.example.magnugadrift.classes.Menu.Drinks.BevandeSpinaMI
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.FoodMagnugaMenu
import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem
import com.example.magnugadrift.classes.Menu.Foods.PizzaNapoletanaMI
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.FormatoType
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory
import com.example.magnugadrift.classes.Menu.Enums.MenuMode
import com.example.magnugadrift.classes.Menu.Enums.PiecesSizes
import com.example.magnugadrift.classes.Menu.Foods.FrittiMI
import com.example.magnugadrift.classes.Menu.Foods.HamburgerMI
import com.example.magnugadrift.classes.Menu.Foods.HamburgerPatateMI
import com.example.magnugadrift.classes.Menu.Foods.SpianataMI
import com.example.magnugadrift.classes.Menu.Foods.SpianataRipienaMI
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.example.magnugadrift.classes.UIContent
import com.example.magnugadrift.databinding.ActivityMainBinding
import com.example.magnugadrift.utils.ReadJSONFromAssets
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var foodMagnuMenu: FoodMagnugaMenu
        lateinit var drinkMagnuMenu: DrinkMagnugaMenu
        lateinit var dessertMagnuMenu: DessertMagnugaMenu
        lateinit var lstOrder: ArrayList<MagnugaOrderItem>
    }


    private lateinit var binding: ActivityMainBinding
    lateinit var data: UIContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonStr = loadMenuEntries()
        data = Gson().fromJson(jsonStr, UIContent::class.java)
        food_CreateMenu(data)
        drink_CreateMenu(data)
        dessert_CreateMenu(data)
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
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.TablayoutMenuPager_vp_ViewPager -> {
                    val argument = NavArgument.Builder().setDefaultValue(MenuMode.CONSULTATION).build()
                    destination.addArgument("SELECTED_MENU_MODE", argument)
                }
            }            }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun loadMenuEntries() : String {
        return ReadJSONFromAssets(baseContext, "magnuga_menu.json")
    }

    fun getJsonData(): UIContent {
        return data
    }

    fun food_CreateMenu(jsonFile: UIContent) {
        val pizzeNapoletane = ArrayList<MagnugaMenuItem>()
        val pizzeNapoletaneJson = jsonFile.food_list.pizze_napoletane
        val spianate = ArrayList<MagnugaMenuItem>()
        val spianateJson = jsonFile.food_list.spianate
        val spianateRipiene = ArrayList<MagnugaMenuItem>()
        val spianateRipieneJson = jsonFile.food_list.spianate_ripiene
        val fritti = ArrayList<MagnugaMenuItem>()
        val frittiJson = jsonFile.food_list.fritti
        val hamburger = ArrayList<MagnugaMenuItem>()
        val hamburgerJson = jsonFile.food_list.hamburger
        val hamburgerPatate = ArrayList<MagnugaMenuItem>()
        val hamburgerPatateJson = jsonFile.food_list.hamburger_con_patate

        getPizzeNapoletane(pizzeNapoletaneJson, pizzeNapoletane)
        getSpianate(spianateJson, spianate)
        getSpianateRipiene(spianateRipieneJson, spianateRipiene)
        getFritti(frittiJson, fritti)
        getHamburger(hamburgerJson, hamburger)
        getHamburgerPatate(hamburgerPatateJson, hamburgerPatate)

        foodMagnuMenu = FoodMagnugaMenu(pizzeNapoletane,
            spianate,
            spianateRipiene,
            fritti,
            hamburger,
            hamburgerPatate)
    }

    fun drink_CreateMenu(jsonFile: UIContent) {
        val bevande_spina = ArrayList<MagnugaMenuItem>()
        val bevande_spinaJson = jsonFile.drink_list.bevande_spina
        val bevande_lattina =  ArrayList<MagnugaMenuItem>()
        val bevande_lattinaJson = jsonFile.drink_list.bevande_lattina
        val bevande_bottiglia = ArrayList<MagnugaMenuItem>()
        val bevande_bottigliaJson = jsonFile.drink_list.bevande_bottiglia

        getBevandeSpina(bevande_spinaJson, bevande_spina)

        drinkMagnuMenu = DrinkMagnugaMenu(bevande_spina)

    }

    fun dessert_CreateMenu(jsonFile: UIContent) {
        val fette_torta = ArrayList<MagnugaMenuItem>()
        val fette_tortaJson = jsonFile.dessert_list.fette_torta
        val donuts =  ArrayList<MagnugaMenuItem>()
        val donutsJson = jsonFile.dessert_list.donuts
        val altri_dolci = ArrayList<MagnugaMenuItem>()
        val altri_dolciJson = jsonFile.dessert_list.altri_dolci

        getFetteTorta(fette_tortaJson, fette_torta)
        getDonuts(donutsJson, donuts)
        getAltriDolci(altri_dolciJson, altri_dolci)

        dessertMagnuMenu = DessertMagnugaMenu(fette_torta, donuts, altri_dolci)
    }

    fun getPizzeNapoletane(pizzeNapoletane: List<FoodFamiliesContent.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (p in pizzeNapoletane) {
            val nPizza = PizzaNapoletanaMI(
                p.nome, p.descrizione, p.prezzo.toTypedArray(),
                FoodType.values()[p.tipo], ingredientsToArray(p.ingredienti), MenuCategory.CIBO,
                sizesToArray(p.taglie)
            )
            lst.add(nPizza)
        }
    }

    fun getSpianate(spianate: List<FoodFamiliesContent.FoodEntry>, lst: ArrayList<MagnugaMenuItem>) {
        for (s in spianate) {
            val nSpianata = SpianataMI(
                s.nome, s.descrizione, s.prezzo.toTypedArray(),
                FoodType.values()[s.tipo], ingredientsToArray(s.ingredienti), MenuCategory.CIBO,
                sizesToArray(s.taglie)
            )
            lst.add(nSpianata)
        }
    }

    fun getSpianateRipiene(spianateRipiene: List<FoodFamiliesContent.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (s in spianateRipiene) {
            val nSpianata = SpianataRipienaMI(
                s.nome, s.descrizione, s.prezzo.toTypedArray(),
                FoodType.values()[s.tipo], ingredientsToArray(s.ingredienti),
                MenuCategory.CIBO, sizesToArray(s.taglie)
            )
            lst.add(nSpianata)
        }
    }

    fun getFritti(fritti: List<FoodFamiliesContent.FoodEntry>,
                  lst: ArrayList<MagnugaMenuItem>) {
        for (f in fritti) {
            val nFritto = FrittiMI(
                f.nome, f.descrizione, f.prezzo.toTypedArray(),
                FoodType.values()[f.tipo], ingredientsToArray(f.ingredienti), sizesToArray(f.taglie),
                MenuCategory.CIBO, piecesToArray(f.pezzi)
            )
            lst.add(nFritto)
        }
    }

    fun getHamburger(hamburger: List<FoodFamiliesContent.FoodEntry>,
                     lst: ArrayList<MagnugaMenuItem>) {
        for (h in hamburger) {
            val nHamburger = HamburgerMI(
                h.nome, h.descrizione, h.prezzo.toTypedArray(),
                FoodType.values()[h.tipo], ingredientsToArray(h.ingredienti), MenuCategory.CIBO,
                if (h.formato != null) FormatoType.fromInt(h.formato) else null
            )
            lst.add(nHamburger)
        }
    }

    fun getHamburgerPatate(hamburger_patate: List<FoodFamiliesContent.FoodEntry>,
                           lst: ArrayList<MagnugaMenuItem>) {
        for (h in hamburger_patate) {
            val nHamburger = HamburgerPatateMI(
                h.nome, h.descrizione, h.prezzo.toTypedArray(),
                FoodType.values()[h.tipo], ingredientsToArray(h.ingredienti), MenuCategory.CIBO,
                if (h.formato != null) FormatoType.fromInt(h.formato) else null
            )
            lst.add(nHamburger)
        }
    }

    fun getBevandeSpina(bevandeSpina: List<DrinkFamiliesContent.DrinkEntry>,
                        lst: ArrayList<MagnugaMenuItem>) {
        for (b in bevandeSpina) {
            val nBevanda = BevandeSpinaMI(
                b.nome, sizesToArray(b.taglie), b.prezzo.toTypedArray(), MenuCategory.BERE,
                FoodType.values()[b.tipo]
            )
            lst.add(nBevanda)
        }
    }

    fun getFetteTorta(fette_torta: List<DessertFamiliesContent.DessertEntry>,
                      lst: ArrayList<MagnugaMenuItem>) {
        for (f in fette_torta) {
            val nTorta = FetteTortaMI(
                f.nome, f.descrizione, f.prezzo.toTypedArray(), MenuCategory.DOLCI,
                FoodType.values()[f.tipo]
            )
            lst.add(nTorta)
        }
    }

    fun getDonuts(donuts: List<DessertFamiliesContent.DessertEntry>,
                      lst: ArrayList<MagnugaMenuItem>) {
        for (d in donuts) {
            val nDonut = DonutsMI(
                d.nome, d.prezzo.toTypedArray(), MenuCategory.DOLCI,
                FoodType.values()[d.tipo]
            )
            lst.add(nDonut)
        }
    }

    fun getAltriDolci(altri_dolci: List<DessertFamiliesContent.DessertEntry>,
                      lst: ArrayList<MagnugaMenuItem>) {
        for (a in altri_dolci) {
            val nDolce = AltriDolciMI(
                a.nome, a.descrizione, a.prezzo.toTypedArray(), MenuCategory.DOLCI,
                FoodType.values()[a.tipo]
            )
            lst.add(nDolce)
        }
    }

    fun ingredientsToArray(ingredients: List<String>?): ArrayList<String>? {
        return if (ingredients != null) {
            val lst = mutableListOf<String>()
            for (t in ingredients) {
                lst.add(t.replaceFirstChar { firstChar -> firstChar.uppercase() })
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