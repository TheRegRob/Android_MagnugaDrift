package com.example.magnugadrift.classes.Menu

class MagnugaMenu(pizzeNapoletane: ArrayList<MagnugaMenuItem>) {
    //region Properties
    private val _pizzeNapoletane: ArrayList<MagnugaMenuItem>
    //endregion

    //region Getters and Setters
    fun getPizzeNapoletane(): ArrayList<MagnugaMenuItem> {
        return _pizzeNapoletane
    }
    //endregion

    //region Constructors
    init {
        _pizzeNapoletane = pizzeNapoletane
    }
    //endregion
}