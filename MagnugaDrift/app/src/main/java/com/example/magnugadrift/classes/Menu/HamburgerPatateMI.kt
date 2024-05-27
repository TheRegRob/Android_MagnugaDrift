package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodFamilies
import com.example.magnugadrift.classes.Menu.Enums.FoodType

class HamburgerPatateMI (name: String,
                         descrizione: String?,
                         price: Array<Float>,
                         type: FoodType,
                         ingredients: ArrayList<String>?) :
    MagnugaMenuItem(FoodFamilies.HAMBURGER_PATATE, name, descrizione, ingredients, price, type) {
    //region Properties
    private val _ingredients: ArrayList<String>?
    private val _prices: Array<Float>
    private val _enricheables: ArrayList<AggiunteEntry>
    private val _aggiunte: ArrayList<AggiuntaType>?
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
        _enricheables = arrayListOf(
            AggiunteEntry.HM_AGGIUNTE,
            AggiunteEntry.HM_DOPPIA_CARNE,
            AggiunteEntry.HM_DOPPIA_CARNE_MAXI,
            AggiunteEntry.HM_DOPPIA_CARNE_GIGA)
    }
    //endregion

    //region Methods and functions
    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getCurrentPrice(): Float {
        return _prices[0]
    }
    //endregion
}