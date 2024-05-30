package com.example.magnugadrift.classes.Menu.Enums

import com.example.magnugadrift.R

enum class FoodFamilies(value: Int) {
    PIZZA_NAPOLETANA(0),
    SPIANATE(1),
    SPIANATE_RIPIENE(2),
    FRITTI(3),
    HAMBURGER(4),
    HAMBURGER_PATATE(5);

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }

    fun getThumbnailIdx(): Int? {
        return when (value) {
            0 -> R.drawable.thbn_pizza_napoletana
            1 -> R.drawable.thbn_spianata
            2 -> R.drawable.thbn_spianata_ripiena
            3 -> R.drawable.thbn_fritti
            4 -> R.drawable.thbn_hamburger
            5 -> R.drawable.thbn_hamburger_patate
            else -> null
        }
    }

    fun getString(): String {
        return when (value) {
            0 -> "Pizza napoletana"
            1 -> "Spianata"
            2 -> "Spianata ripiena"
            3 -> "Fritto"
            4 -> "Hamburger (senza patate)"
            5 -> "Hamburger con patate"
            else -> "unexp err"
        }
    }
}