package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.FoodType

abstract class MagnugaMenuItem(imgResId: Int,
                               name: String,
                               price: Array<Float>,
                               type: FoodType
) {
    //region Properties
    val _menuItemImage: Int
    val _menuItemName: String
    val _menuItemPrice: Array<Float>
    val _foodType: FoodType
    //endregion

    //region Getters and Setters
    fun menuItemImage(): Int {
        return _menuItemImage
    }
    fun menuItemName(): String {
        return _menuItemName
    }
    fun menuItemPrice(): Array<Float> {
        return _menuItemPrice
    }
    fun foodType(): FoodType {
        return _foodType
    }
    //endregion

    //region Constructors
    init {
        _menuItemImage = imgResId
        _menuItemName = name
        _menuItemPrice = price
        _foodType = type
    }
    //endregion
}