package com.example.magnugadrift.classes.Menu.Enums

enum class MenuMode(value: Int) {
    CONSULTATION(0),
    ORDER(1);

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }

    companion object {
        fun fromInt(value: Int) = MenuMode.values().first { it.value == value }
    }
}