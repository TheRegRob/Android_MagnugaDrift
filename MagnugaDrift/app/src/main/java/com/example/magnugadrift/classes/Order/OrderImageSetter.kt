package com.example.magnugadrift.classes.Order

import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.Enums.FoodFamilies
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem

class OrderImageSetter(magnugaMenuItem: MagnugaMenuItem) {
    private var imageSetterIdx: Int

    init {
        imageSetterIdx = when (magnugaMenuItem.menuItemFamily()) {
            FoodFamilies.PIZZA_NAPOLETANA -> R.drawable.pizza_res //Impostare con gli indici corretti
            FoodFamilies.SPIANATE -> 0
            FoodFamilies.SPIANATE_RIPIENE -> 0
            FoodFamilies.FRITTI -> 0
            FoodFamilies.HAMBURGER -> 0
            FoodFamilies.HAMBURGER_PATATE -> 0
        }
    }

    fun getImageIdx(): Int {
        return imageSetterIdx;
    }
}