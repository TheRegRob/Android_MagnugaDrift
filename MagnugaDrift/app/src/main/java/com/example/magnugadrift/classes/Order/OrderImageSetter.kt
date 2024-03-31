package com.example.magnugadrift.classes.Order

import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.FoodFamilies
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem

class OrderImageSetter(magnugaMenuItem: MagnugaMenuItem) {
    private var imageSetterIdx: Int

    init {
        when (magnugaMenuItem.menuItemFamily()) {
            FoodFamilies.PIZZA_NAPOLETANA -> imageSetterIdx = R.drawable.pizza_res //Impostare con gli indici corretti
            FoodFamilies.SPIANATE -> imageSetterIdx = 0
            FoodFamilies.SPIANATE_RIPIENE -> imageSetterIdx = 0
        }
    }

    fun getImageIdx(): Int {
        return imageSetterIdx;
    }
}