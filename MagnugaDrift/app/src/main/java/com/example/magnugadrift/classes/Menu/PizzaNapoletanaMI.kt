package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Enrich.isEnrichable
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes

class PizzaNapoletanaMI(name: String,
                        price: Array<Float>,
                        type: FoodType,
                        ingredients: ArrayList<String>,
                        sizes: Array<PizzaSizes>) :
                        MagnugaMenuItem(FoodImages.PIZZA_NAPOLETANA, name, ingredients, price, type),
                        isEnrichable {
    //region Properties
    private val _ingredients: ArrayList<String>
    private val _sizes: Array<PizzaSizes>
    //endregion

    //region Getters and Setters
    fun pizzaNapoletanaIngredients(): ArrayList<String> {
        return _ingredients
    }
    fun pizzaNapoletanaSizes(): Array<PizzaSizes> {
        return _sizes
    }
    //endregion

    //region Constructors
    init {
        _ingredients = ingredients
        _sizes = sizes
    }
    //endregion
}