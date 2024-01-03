package com.example.magnugadrift.classes.Enrich

class EnrichItem(name: String, priceIncrease: Array<Float>, ) {
    val _enrichName: String
    val _priceIncrease: Array<Float>

    fun enrichName(): String {
        return _enrichName
    }

    fun priceIncrease(): Array<Float> {
        return _priceIncrease
    }

    init {
        _enrichName = name
        _priceIncrease = priceIncrease
    }
}