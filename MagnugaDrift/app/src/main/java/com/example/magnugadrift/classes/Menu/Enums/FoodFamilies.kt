package com.example.magnugadrift.classes.Menu.Enums

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