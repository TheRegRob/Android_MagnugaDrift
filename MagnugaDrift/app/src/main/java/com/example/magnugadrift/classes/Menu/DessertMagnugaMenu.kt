package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem

class DessertMagnugaMenu(
    fette_torta: ArrayList<MagnugaMenuItem>,
    donuts: ArrayList<MagnugaMenuItem>,
    altri_dolci: ArrayList<MagnugaMenuItem>) {

    //region Properties
    private val _fette_torta: ArrayList<MagnugaMenuItem>
    private val _donuts: ArrayList<MagnugaMenuItem>
    private val _altri_dolci: ArrayList<MagnugaMenuItem>
    //endregion

    // region Getters and Setters
    fun getFetteTorta(): ArrayList<MagnugaMenuItem> {
        return _fette_torta
    }

    fun getDonuts(): ArrayList<MagnugaMenuItem> {
        return _donuts
    }

    fun getAltriDolci(): ArrayList<MagnugaMenuItem> {
        return _altri_dolci
    }
    //endregion

    //region Constructors
    init {
        _fette_torta = fette_torta
        _donuts = donuts
        _altri_dolci = altri_dolci
    }
    //endregion

    //region Methods and Functions
    fun GetAllDesserts() : ArrayList<MagnugaMenuItem> {
        val menuLst = ArrayList<MagnugaMenuItem>()
        for (i in getFetteTorta())
            menuLst.add(i)
        for (i in getDonuts())
            menuLst.add(i)
        for (i in getAltriDolci())
            menuLst.add(i)
        return menuLst
    }
    //endregion
}