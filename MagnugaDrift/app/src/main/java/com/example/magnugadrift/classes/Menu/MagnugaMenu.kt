package com.example.magnugadrift.classes.Menu

class MagnugaMenu(pizzeNapoletane: ArrayList<MagnugaMenuItem>,
                  spianate: ArrayList<MagnugaMenuItem>,
                  spianateRipiene: ArrayList<MagnugaMenuItem>) {
    //region Properties
    private val _pizzeNapoletane: ArrayList<MagnugaMenuItem>
    private val _spianate: ArrayList<MagnugaMenuItem>
    private val _spianateRipiene: ArrayList<MagnugaMenuItem>
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
    //endregion

    //region Constructors
    init {
        _pizzeNapoletane = pizzeNapoletane
        _spianate = spianate
        _spianateRipiene = spianateRipiene
    }

    fun getAllMenu() : ArrayList<MagnugaMenuItem> {
        val menuLst = ArrayList<MagnugaMenuItem>()
        for (i in getPizzeNapoletane())
            menuLst.add(i)
        for (i in getSpianate())
            menuLst.add(i)
        for (i in getSpianateRipiene())
            menuLst.add(i)
        return menuLst
    }
    //endregion
}