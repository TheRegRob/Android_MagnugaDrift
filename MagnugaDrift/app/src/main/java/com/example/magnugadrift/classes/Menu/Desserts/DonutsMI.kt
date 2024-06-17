package com.example.magnugadrift.classes.Menu.Desserts

import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.MenuCategory
import com.example.magnugadrift.classes.Menu.Enums.MenuItemFamilies
import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem

class DonutsMI(name: String,
               price: Array<Float>,
               category: MenuCategory,
               type: FoodType
) : MagnugaMenuItem(
    MenuItemFamilies.DONUTS, name, null, null, price, category, type) {
    //region Properties
    private val _prices: Array<Float>
    //endregion

    override fun getSizesPrices(): Array<Float> {
        return _prices
    }
    //endregion

    //region Constructors
    init {
        _prices = price
    }
    //endregion

    //region Methods and functions
    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getCurrentPrice(): Float {
        return _prices[0]
    }
    //endregion     {
}