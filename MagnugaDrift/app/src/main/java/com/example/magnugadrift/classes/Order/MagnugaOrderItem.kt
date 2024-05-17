package com.example.magnugadrift.classes.Order

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.PiecesSizes
import com.example.magnugadrift.classes.Menu.FoodFamilies
import com.example.magnugadrift.classes.Menu.MagnugaMenuItem
import java.io.Serializable

data class MagnugaOrderItem(val magnugaMenuItem: MagnugaMenuItem) : Serializable {
    //region Properties
    private val _foodImage: Int
    private val _rating: Int
    private val _price: Float
    private val _type: FoodType
    private val _magnugaItem: MagnugaMenuItem
    private var _family: FoodFamilies
    private var _name: String
    private var _size: FoodSizes?
    private var _pieces: Pair<PiecesSizes, Int>?
    private var _ingredients: ArrayList<Pair<String, Boolean>>?
    private var _aggiunte: ArrayList<AggiuntaType>?
    private var _enricheables: ArrayList<AggiuntaType>?
    private var _note: String
    private var _finalPrice: Float
    //endregion

    //region Getters and Setters
    fun getOrderItemFoodImage(): Int {
        return _foodImage
    }
    fun getOrderItemRating(): Int {
        return _rating
    }
    fun getOrderItemPrice(): Float {
        when (_size) {
            FoodSizes.S -> return _magnugaItem.getSizesPrices()[0]
            FoodSizes.M -> return _magnugaItem.getSizesPrices()[1]
            FoodSizes.L -> return _magnugaItem.getSizesPrices()[2]
            else -> {
                return 0.0f
            }
        }
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
    fun getOrderItemSize():FoodSizes? {
        return _size
    }
    fun getOrderItemPieces(): Pair<PiecesSizes, Int>? {
        return _pieces
    }
    fun getOrderItemIngredients(): ArrayList<Pair<String, Boolean>>? {
        return _ingredients
    }
    fun setOrderItemIngredients(nList: ArrayList<Pair<String, Boolean>>?) {
        _ingredients = nList
    }
    fun getOrderItemAggiunte(): ArrayList<AggiuntaType>? {
        return _aggiunte
    }
    fun addToOrderItemAggiunte(aggiunta: AggiuntaType) {
        _aggiunte?.add(aggiunta)
    }
    fun getOrderItemEnricheables(): ArrayList<AggiuntaType>? {
        return _enricheables
    }
    fun getOrderItemNote(): String {
        return _note
    }
    fun getFinalPrice(): Float {
        return _finalPrice
    }
    fun setFinalPrice(v: Float) {
        _finalPrice = v
    }
    //endregion

    //region Constructors
    init {
        _magnugaItem = magnugaMenuItem
        _foodImage = magnugaMenuItem.getResourceImage()
        _rating = 0 // Dovrà essere recuperato dalla memoria
        _price = magnugaMenuItem.getCurrentPrice()
        _type = magnugaMenuItem.getFoodType()
        _family = magnugaMenuItem.menuItemFamily()
        _name = magnugaMenuItem.menuItemName()
        _size = magnugaMenuItem.getCurrentSize()
        _pieces = magnugaMenuItem.getCurrentPieces()
        _ingredients = ArrayList()
        if (magnugaMenuItem.menuItemIngredients().isNotEmpty()) {
            for (ingredient in magnugaMenuItem.menuItemIngredients())
                _ingredients!!.add(Pair(ingredient, true))
        }
        _finalPrice = 0f
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

    //region Methods and Functions
    fun increaseSize() {
        _size = if (_size == _magnugaItem.getSizesValues().last()) {
            _magnugaItem.getSizesValues()[0]
        } else {
            _magnugaItem.getSizesValues()[_magnugaItem.getSizesValues().indexOf(_size) + 1]
        }
    }
    //endregion
}