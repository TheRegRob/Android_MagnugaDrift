package com.example.magnugadrift.classes.Menu.Foods

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.MenuItemFamilies
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.FormatoType
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory

class HamburgerPatateMI (name: String,
                         descrizione: String?,
                         price: Array<Float>,
                         type: FoodType,
                         ingredients: ArrayList<String>?,
                         category: MenuCategory,
                         formato: FormatoType?) :
    MagnugaMenuItem(MenuItemFamilies.HAMBURGER_PATATE, name, descrizione, ingredients, price,
        category, type) {
    //region Properties
    private val _ingredients: ArrayList<String>?
    private val _prices: Array<Float>
    private val _enricheables: ArrayList<AggiunteEntry>
    private val _aggiunte: ArrayList<AggiuntaType>?
    private val _formato: FormatoType?
    //endregion

    //region Getters and Setters
    fun getIngredients(): ArrayList<String>? {
        return _ingredients
    }
    override fun getSizesPrices(): Array<Float> {
        return _prices
    }
    //endregion

    //region Constructors
    init {
        _ingredients = ingredients
        _prices = price
        _aggiunte = ArrayList()
        _formato = formato
        _enricheables = arrayListOf(
            AggiunteEntry.HM_AGGIUNTE)
        if (formato != null) {
            _enricheables.add(formato.getAggiuntaEntry())
        }
    }
    //endregion

    //region Methods and functions
    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getCurrentPrice(): Float {
        return _prices[0]
    }

    override fun getEnricheables(): ArrayList<AggiunteEntry> {
        return _enricheables
    }

    override fun getAggiunte(): ArrayList<AggiuntaType>? {
        return _aggiunte
    }
    //endregion
}