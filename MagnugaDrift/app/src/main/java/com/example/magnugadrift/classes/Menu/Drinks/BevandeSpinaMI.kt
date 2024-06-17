package com.example.magnugadrift.classes.Menu.Drinks

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory
import com.example.magnugadrift.classes.Menu.Enums.MenuItemFamilies
import com.example.magnugadrift.classes.Menu.Enums.PiecesSizes
import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem

class BevandeSpinaMI(name: String,
                     sizes: ArrayList<FoodSizes>?,
                     price: Array<Float>,
                     category: MenuCategory,
                     type: FoodType) : MagnugaMenuItem (
        MenuItemFamilies.BEVANDE_SPINA, name, null, null, price, category, type) {
    //region Properties
    private val _sizes: ArrayList<FoodSizes>?
    private val _prices: Array<Float>
    private var _curSize: FoodSizes?
    //endregion

    override fun getSizesValues(): ArrayList<FoodSizes>? {
        return _sizes
    }
    override fun getSizesPrices(): Array<Float> {
        return _prices
    }
    //endregion

    //region Constructors
    init {
        _sizes = sizes
        _prices = price
        _curSize = sizes?.get(0)
    }
    //endregion

    //region Methods and functions
    override fun getCurrentSize(): FoodSizes? {
        return _curSize
    }

    override fun increaseCurrSize() {
        _curSize = if (_curSize == _sizes!!.last()) {
            _sizes[0]
        } else {
            _sizes[_sizes.indexOf(_curSize) + 1]
        }
    }

    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getSizesString(sizes: FoodSizes): String {
        return when (sizes) {
            FoodSizes.S -> "Piccola"
            FoodSizes.M -> "Media"
            FoodSizes.L -> "Grande"
        }
    }

    override fun getCurrentPrice(): Float {
        return if (_sizes != null) {
            _prices[getCurrentSize()!!.getValue()]
        } else {
            _prices[0]
        }
    }
    //endregion
}