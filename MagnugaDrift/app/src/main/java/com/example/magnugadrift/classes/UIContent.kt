package com.example.magnugadrift.classes

class UIContent(
    val food_list: FoodFamilies
)
class FoodFamilies(
    val pizze_napoletane: List<FoodEntry>,
    val spianate: List<FoodEntry>,
    val spianate_ripiene: List<FoodEntry>,
    val fritti: List<FoodEntry>,
    val hamburger: List<FoodEntry>,
    val hamburger_con_patate: List<FoodEntry>
) {
class FoodEntry(
    val nome: String,
    val descrizione: String?,
    val ingredienti: List<String>?,
    val taglie: List<Int>?,
    val pezzi: List<Int>?,
    val prezzo: List<Float>,
    val tipo: Int,
    val formato: Int?
)
}
