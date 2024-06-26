package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem

class DrinkMagnugaMenu(
    bevande_spina: ArrayList<MagnugaMenuItem>,
    bevande_lattina: ArrayList<MagnugaMenuItem>,
    bevande_bottiglia: ArrayList<MagnugaMenuItem>) {

    //region Properties
    private val _bevande_spina: ArrayList<MagnugaMenuItem>
    private val _bevande_lattina: ArrayList<MagnugaMenuItem>
    private val _bevande_bottiglia: ArrayList<MagnugaMenuItem>
    //endregion

    // region Getters and Setters
    fun getBevandeSpina(): ArrayList<MagnugaMenuItem> {
        return _bevande_spina
    }

    fun getBevandeLattina(): ArrayList<MagnugaMenuItem> {
        return _bevande_lattina
    }

    fun getBevandeBottiglia(): ArrayList<MagnugaMenuItem> {
        return _bevande_bottiglia
    }
    //endregion

    //region Constructors
    init {
        _bevande_spina = bevande_spina
        _bevande_lattina = bevande_lattina
        _bevande_bottiglia = bevande_bottiglia
    }
    //endregion

    //region Methods and Functions
    fun GetAllDrinks() : ArrayList<MagnugaMenuItem> {
        val menuLst = ArrayList<MagnugaMenuItem>()
        for (i in getBevandeSpina())
            menuLst.add(i)
        for (i in getBevandeLattina())
            menuLst.add(i)
        for (i in getBevandeBottiglia())
            menuLst.add(i)
        return menuLst
    }
    //endregion
}