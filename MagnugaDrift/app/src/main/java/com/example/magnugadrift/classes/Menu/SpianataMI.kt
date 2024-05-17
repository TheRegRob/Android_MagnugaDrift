package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes

class SpianataMI(name: String,
                 price: Array<Float>,
                 type: FoodType,
                 ingredients: ArrayList<String>,
                 sizes: ArrayList<FoodSizes>) :
    MagnugaMenuItem(FoodFamilies.SPIANATE, name, ingredients, price, type, false, arrayListOf()) {
    //region Properties
    private val _ingredients: ArrayList<String>
    private val _sizes: ArrayList<FoodSizes>
    private val _prices: Array<Float>
    private var _curSize: FoodSizes
    //endregion

    //region Getters and Setters
    fun spianataIngredients(): ArrayList<String> {
        return _ingredients
    }
    fun spianataSizes(): ArrayList<FoodSizes> {
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
    //region Methods and functions
    override fun getSizesPrices(): Array<Float> {
        return _prices
    }

    override fun getSizesValues(): ArrayList<FoodSizes> {
        return _sizes
    }

    override fun getCurrentSize(): FoodSizes? {
        return _curSize
    }

    override fun increaseCurrSize() {
        _curSize = if (_curSize == _sizes.last()) {
            _sizes[0]
        } else {
            _sizes[_sizes.indexOf(_curSize) + 1]
        }
    }

    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getTaglie(): ArrayList<FoodSizes> {
        return _sizes
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