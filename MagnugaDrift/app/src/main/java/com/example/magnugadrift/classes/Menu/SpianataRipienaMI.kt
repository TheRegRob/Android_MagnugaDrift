package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes

class SpianataRipienaMI (name: String,
                         price: Array<Float>,
                         type: FoodType,
                         ingredients: ArrayList<String>,
                         sizes: ArrayList<PizzaSizes>) :
    MagnugaMenuItem(FoodFamilies.SPIANATE_RIPIENE, name, ingredients, price, type, false, arrayListOf()) {
    //region Properties
    private val _ingredients: ArrayList<String>
    private val _sizes: ArrayList<PizzaSizes>
    private val _prices: Array<Float>
    private var _curSize: PizzaSizes
    //endregion

    //region Getters and Setters
    fun spianataIngredients(): ArrayList<String> {
        return _ingredients
    }
    fun spianataSizes(): ArrayList<PizzaSizes> {
        return _sizes
    }
    //endregion

    //region Constructors
    init {
        _ingredients = ingredients
        _sizes = sizes
        _prices = price
        _curSize = sizes[0]
    }
    //endregion
    override fun getCurrentPrice(): Float {
        when (_curSize) {
            PizzaSizes.PICCOLA -> return _prices[0]
            PizzaSizes.MEDIA -> return _prices[1]
            PizzaSizes.MAXI -> return _prices[2]
            else -> {
                return 0.0f
            }
        }
    }

}