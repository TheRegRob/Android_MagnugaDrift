package com.example.magnugadrift.classes.Menu.Foods

import com.example.magnugadrift.classes.Menu.Enums.MenuItemFamilies
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes

class SpianataRipienaMI (name: String,
                         descrizione: String?,
                         price: Array<Float>,
                         type: FoodType,
                         ingredients: ArrayList<String>?,
                         sizes: ArrayList<FoodSizes>?) :
    MagnugaMenuItem(MenuItemFamilies.SPIANATE_RIPIENE, name, descrizione, ingredients, price, type) {
    //region Properties
    private val _ingredients: ArrayList<String>?
    private val _sizes: ArrayList<FoodSizes>?
    private val _prices: Array<Float>
    private var _curSize: FoodSizes?
    //endregion

    //region Getters and Setters
    fun spianataIngredients(): ArrayList<String>? {
        return _ingredients
    }
    fun spianataSizes(): ArrayList<FoodSizes>? {
        return _sizes
    }
    //endregion

    //region Constructors
    init {
        _ingredients = ingredients
        _sizes = sizes
        _prices = price
        _curSize = sizes?.get(0)
    }
    //endregion
    //region Methods and functions
    override fun getSizesPrices(): Array<Float> {
        return _prices
    }

    override fun getSizesValues(): ArrayList<FoodSizes>? {
        return _sizes
    }

    override fun getCurrentSize(): FoodSizes? {
        return _curSize
    }

    override fun increaseCurrSize() {
        _curSize = if (_curSize == _sizes?.last()) {
            _sizes?.get(0)
        } else {
            _sizes?.get(_sizes.indexOf(_curSize) + 1)
        }
    }

    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getSizesString(sizes: FoodSizes): String {
        return when (sizes) {
            FoodSizes.S -> "Piccola"
            FoodSizes.M -> "Media"
            FoodSizes.L -> "Maxi"
        }
    }

    override fun getCurrentPrice(): Float {
        when (_curSize) {
            FoodSizes.S -> return _prices[0]
            FoodSizes.M -> return _prices[1]
            FoodSizes.L -> return _prices[2]
            else -> {
                return 0.0f
            }
        }
    }
    //endregion
}