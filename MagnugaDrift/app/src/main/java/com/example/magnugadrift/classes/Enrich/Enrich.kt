package com.example.magnugadrift.classes.Enrich

abstract class Enrich(addableIngredients: List<String>) {
    val _addableIngredients: List<String>

    fun GetAddableIngredients(): List<String>{
        return _addableIngredients
    }

    init {
        _addableIngredients = addableIngredients
    }
}