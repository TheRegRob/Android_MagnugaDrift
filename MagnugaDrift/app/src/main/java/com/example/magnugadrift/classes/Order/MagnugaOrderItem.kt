package com.example.magnugadrift.classes.Order

import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.classes.Menu.FoodFamilies
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem

class MagnugaOrderItem(magnugaMenuItem: MagnugaMenuItem) {
    //region Properties
    private val _foodImage: Int
    private val _rating: Int
    private val _price: Float
    private val _type: FoodType
    private var _family: FoodFamilies
    private var _name: String
    private var _size: PizzaSizes?
    private var _pieces: Int?
    private var _ingredients: ArrayList<String>?
    private var _aggiunte: ArrayList<AggiunteEntry>?
    private var _note: String
    //endregion

    //region Getters and Setters

    //endregion

    //region Constructors
    init {
        _foodImage = OrderImageSetter(magnugaMenuItem).getImageIdx()
        _rating = 0 // Dovrà essere recuperato dalla memoria
        _price = magnugaMenuItem.getCurrentPrice()
        _type = magnugaMenuItem.getFoodType()
        _family = magnugaMenuItem.menuItemFamily()
        _name = magnugaMenuItem.menuItemName()
        _size = magnugaMenuItem.getCurrentSize()
        _pieces = magnugaMenuItem.getCurrentPieces()
        _ingredients = magnugaMenuItem.menuItemIngredients()
        _aggiunte = magnugaMenuItem.getAggiunte()
        _note = "" // Dovrà essere recuperato dalla memoria
    }
    //endregion
}