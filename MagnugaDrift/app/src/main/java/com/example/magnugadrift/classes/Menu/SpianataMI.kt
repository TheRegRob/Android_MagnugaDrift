package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes

class SpianataMI(name: String,
                 price: Array<Float>,
                 type: FoodType,
                 ingredients: ArrayList<String>,
                 sizes: ArrayList<PizzaSizes>) :
    MagnugaMenuItem(FoodFamilies.SPIANATE, name, ingredients, price, type, false, arrayListOf()) {
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
    //region Methods and functions
    override fun getCurrentSize(): PizzaSizes? {
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

    override fun getTaglie(): ArrayList<PizzaSizes> {
        return _sizes
    }

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
    //endregion
}