package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Foods.MagnugaMenuItem

class FoodMagnugaMenu(pizzeNapoletane: ArrayList<MagnugaMenuItem>,
                      spianate: ArrayList<MagnugaMenuItem>,
                      spianateRipiene: ArrayList<MagnugaMenuItem>,
                      fritti: ArrayList<MagnugaMenuItem>,
                      hamburger: ArrayList<MagnugaMenuItem>,
                      hamburgerPatate: ArrayList<MagnugaMenuItem>){
    //region Properties
    private val _pizzeNapoletane: ArrayList<MagnugaMenuItem>
    private val _spianate: ArrayList<MagnugaMenuItem>
    private val _spianateRipiene: ArrayList<MagnugaMenuItem>
    private val _fritti: ArrayList<MagnugaMenuItem>
    private val _hamburger: ArrayList<MagnugaMenuItem>
    private val _hamburgerPatate: ArrayList<MagnugaMenuItem>
    //endregion

    //region Getters and Setters
    fun getPizzeNapoletane(): ArrayList<MagnugaMenuItem> {
        return _pizzeNapoletane
    }
    fun getSpianate(): ArrayList<MagnugaMenuItem> {
        return _spianate
    }

    fun getSpianateRipiene(): ArrayList<MagnugaMenuItem> {
        return _spianateRipiene
    }

    fun getFritti(): ArrayList<MagnugaMenuItem> {
        return _fritti
    }

    fun getHamburger(): ArrayList<MagnugaMenuItem> {
        return _hamburger
    }

    fun getHamburgerPatate(): ArrayList<MagnugaMenuItem> {
        return _hamburgerPatate
    }


    //endregion

    //region Constructors
    init {
        _pizzeNapoletane = pizzeNapoletane
        _spianate = spianate
        _spianateRipiene = spianateRipiene
        _fritti = fritti
        _hamburger = hamburger
        _hamburgerPatate = hamburgerPatate
    }
    //endregion

    //region Methods and Functions
    fun GetAllMenu() : ArrayList<MagnugaMenuItem> {
        val menuLst = ArrayList<MagnugaMenuItem>()
        for (i in getPizzeNapoletane())
            menuLst.add(i)
        for (i in getSpianate())
            menuLst.add(i)
        for (i in getSpianateRipiene())
            menuLst.add(i)
        for (i in getFritti())
            menuLst.add(i)
        for (i in getHamburger())
            menuLst.add(i)
        for (i in getHamburgerPatate())
            menuLst.add(i)
        return menuLst
    }
    //endregion
}