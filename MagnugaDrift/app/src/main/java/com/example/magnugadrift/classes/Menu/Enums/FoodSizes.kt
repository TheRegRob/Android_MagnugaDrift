package com.example.magnugadrift.classes.Menu.Enums

enum class FoodSizes(value: Int){
    S(0),
    M(1),
    L(2);

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }

    fun getString(foodFam: FoodFamilies): String {
        return when (value) {
            0 -> if (foodFam == FoodFamilies.FRITTI) "Piccole" else "Piccola"
            1 -> if (foodFam == FoodFamilies.FRITTI) "Normali" else "Media"
            2 -> if (foodFam == FoodFamilies.FRITTI) "Grandi" else "Maxi"
            else -> "unexp err"
        }
    }
}