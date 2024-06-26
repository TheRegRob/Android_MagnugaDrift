package com.example.magnugadrift.classes

class UIContent(val food_list: FoodFamiliesContent,
                val drink_list: DrinkFamiliesContent,
                val dessert_list: DessertFamiliesContent)

class FoodFamiliesContent(
    val pizze_napoletane: List<FoodEntry>,
    val spianate: List<FoodEntry>,
    val spianate_ripiene: List<FoodEntry>,
    val fritti: List<FoodEntry>,
    val hamburger: List<FoodEntry>,
    val hamburger_con_patate: List<FoodEntry>)
{
    class FoodEntry(
        val nome: String,
        val descrizione: String?,
        val ingredienti: List<String>?,
        val taglie: List<Int>?,
        val pezzi: List<Int>?,
        val prezzo: List<Float>,
        val tipo: Int,
        val formato: Int?)
}

class DrinkFamiliesContent(
    val bevande_spina:  List<DrinkEntry>,
    val bevande_lattina: List<DrinkEntry>,
    val bevande_bottiglia: List<DrinkEntry>
) {
    class DrinkEntry(
        val nome: String,
        val descrizione: String?,
        val taglie: List<Int>?,
        val prezzo: List<Float>,
        val tipo: Int
    )
}

class DessertFamiliesContent(
    val fette_torta:  List<DessertEntry>,
    val donuts: List<DessertEntry>,
    val altri_dolci: List<DessertEntry>
) {
    class DessertEntry(
        val nome: String,
        val descrizione: String?,
        val prezzo: List<Float>,
        val tipo: Int
    )
}
