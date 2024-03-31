package com.example.magnugadrift.classes.Order

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes
import com.example.magnugadrift.classes.Menu.FoodFamilies
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem
import java.io.Serializable

class MagnugaOrderItem(magnugaMenuItem: MagnugaMenuItem) : Serializable {
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
    private var _enricheables: ArrayList<AggiuntaType>?
    private var _note: String
    //endregion

    //region Getters and Setters
    fun getOrderItemFoodImage(): Int {
        return _foodImage
    }
    fun getOrderItemRating(): Int {
        return _rating
    }
    fun getOrderItemPrice(): Float {
        return _price
    }
    fun getOrderItemType(): FoodType {
        return _type
    }
    fun getOrderItemFamily(): FoodFamilies {
        return _family
    }
    fun getOrderItemName(): String {
        return _name
    }
    fun getOrderItemSize():PizzaSizes? {
        return _size
    }
    fun getOrderItemPieces(): Int? {
        return _pieces
    }
    fun getOrderItemIngredients(): ArrayList<String>? {
        return _ingredients
    }
    fun getOrderItemAggiunte(): ArrayList<AggiunteEntry>? {
        return _aggiunte
    }
    fun getOrderItemEnricheables(): ArrayList<AggiuntaType>? {
        return _enricheables
    }
    fun getOrderItemNote(): String {
        return _note
    }
    //endregion

    //region Constructors
    init {
        _foodImage = magnugaMenuItem.getResourceImage()
        _rating = 0 // Dovrà essere recuperato dalla memoria
        _price = magnugaMenuItem.getCurrentPrice()
        _type = magnugaMenuItem.getFoodType()
        _family = magnugaMenuItem.menuItemFamily()
        _name = magnugaMenuItem.menuItemName()
        _size = magnugaMenuItem.getCurrentSize()
        _pieces = magnugaMenuItem.getCurrentPieces()
        _ingredients = magnugaMenuItem.menuItemIngredients()
        var lst_enricheables = arrayListOf<AggiuntaType>()
        if (magnugaMenuItem.getEnricheables() != null) {
            for (enrich in magnugaMenuItem.getEnricheables()!!) {
                lst_enricheables.add(AggiuntaType(enrich))
            }
        }
        _enricheables = lst_enricheables
        _aggiunte = magnugaMenuItem.getAggiunte()
        _note = "" // Dovrà essere recuperato dalla memoria
    }
    //endregion
}