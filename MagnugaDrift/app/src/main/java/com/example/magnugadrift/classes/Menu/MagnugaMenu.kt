package com.example.magnugadrift.classes.Menu

class MagnugaMenu(pizzeNapoletane: ArrayList<PizzaNapoletanaMI>) {
    //region Properties
    private val _pizzeNapoletane: ArrayList<PizzaNapoletanaMI>
    //endregion

    //region Getters and Setters
    fun getPizzeNapoletane(): ArrayList<PizzaNapoletanaMI> {
        return _pizzeNapoletane
    }
    //endregion

    //region Constructors
    init {
        _pizzeNapoletane = pizzeNapoletane
    }
    //endregion
}