package com.example.magnugadrift.classes.Menu.Enums

enum class MenuCategory(value: Int) {
    CIBO(0),
    BERE(1),
    DOLCI(2);

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }

    companion object {
        fun fromInt(value: Int) = MenuCategory.values().first { it.value == value }
    }
}