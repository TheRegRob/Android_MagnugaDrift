package com.example.magnugadrift.classes.Menu.Enums

import com.example.magnugadrift.R

enum class MenuItemFamilies(value: Int) {
    PIZZA_NAPOLETANA(0),
    SPIANATE(1),
    SPIANATE_RIPIENE(2),
    FRITTI(3),
    HAMBURGER(4),
    HAMBURGER_PATATE(5),
    BEVANDE_SPINA(6),
    BEVANDE_LATTINA(7),
    BEVANDE_BOTTIGLIA(8);

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
            6 -> R.drawable.thbn_bevande_spina
            7 -> R.drawable.thbn_bevande_lattina
            8 -> R.drawable.thbn_bevande_bottiglia
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
            6 -> "Bevanda alla spina"
            7 -> "Bevanda in lattina"
            8 -> "Bevanda in bottiglia"
            else -> "unexp err"
        }
    }
}