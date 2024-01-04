package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Enrich.isEnrichable
import com.example.magnugadrift.classes.FoodType
import com.example.magnugadrift.classes.PizzaSizes

class PizzaNapoletanaMI(imgResId: Int,
                        name: String,
                        price: Array<Float>,
                        type: FoodType,
                        ingredients: List<String>,
                        sizes: Array<PizzaSizes>) :
                        MagnugaMenuItem(imgResId, name, price, type),
                        isEnrichable {
    //region Properties
    private val _ingredients: List<String>
    private val _sizes: Array<PizzaSizes>
    //endregion

    //region Getters and Setters
    fun pizzaNapoletanaIngredients(): List<String> {
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