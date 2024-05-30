package com.example.magnugadrift.classes.Menu.Enums

import com.example.magnugadrift.R

enum class FoodType(value: Int) {
    NORMALE(0),
    PICCANTE(1),
    VEGETARIANO(2),
    PICCANTE_VEGETARIANO(3),
    VEGANO(4),
    PICCANTE_VEGANO(5);

    enum class SizeValues {
        SMALL,
        MEDIUM
    }

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }

    fun getIconIdx(): Int? {
        return when (value) {
            0 -> null /* No icona */
            1 -> R.drawable.img_ic_piccante/* Icona fiamma */
            2 -> R.drawable.img_ic_vegetariano/* Icona foglia */
            3 -> R.drawable.img_ic_vegetariano_piccante/* Icona foglia + fiamma*/
            4 -> R.drawable.img_ic_vegano/* Icona V */
            5 -> R.drawable.img_ic_vegano_piccante/* Icona V+ fiamma */
            else -> null /* No icona */
        }
    }

    fun getIconWidth(size: SizeValues): Int {
        return when (value) {
            0, 1, 2, 4 -> if (size == SizeValues.SMALL) 45 else 60
            3, 5 -> if (size == SizeValues.SMALL) 90 else 120
            else -> 0
        }
    }

    fun getTooltipText(): String {
        return when (value) {
            0 -> "" /* No icona */
            1 -> "Piatto piccante"
            2 -> "Piatto vegetariano"
            3 -> "Piatto vegetariano piccante"
            4 -> "Piatto vegano"
            5 -> "Piatto vegano piccante"
            else -> "Unexp Err" /* No icona */
        }
    }
}