package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes

class SpianataMI(name: String,
                 price: Array<Float>,
                 type: FoodType,
                 ingredients: ArrayList<String>,
                 sizes: Array<PizzaSizes>) :
    MagnugaMenuItem(FoodImages.SPIANATE, name, ingredients, price, type) {
    //region Properties
    private val _ingredients: ArrayList<String>
    private val _sizes: Array<PizzaSizes>
    //endregion

    //region Getters and Setters
    fun spianataIngredients(): ArrayList<String> {
        return _ingredients
    }
    fun spianataSizes(): Array<PizzaSizes> {
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