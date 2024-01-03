package com.example.magnugadrift.classes

import com.example.magnugadrift.classes.Enrich.isEnrichable

class PizzaNapoletanaMI(imgResId: Int,
                        name: String,
                        price: Array<Float>,
                        type: FoodType,
                        ingredients: Array<String>,
                        sizes: Array<PizzaSizes>) :
                        MagnugaMenuItem(imgResId, name, price, type),
                        isEnrichable {
    //region Properties
    private val _ingredients: Array<String>
    private val _sizes: Array<PizzaSizes>
    //endregion

    //region Getters and Setters
    fun pizzaNapoletanaIngredients(): Array<String> {
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