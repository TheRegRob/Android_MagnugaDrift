package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.R
import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.PiecesSizes
import java.io.Serializable

open class MagnugaMenuItem(foodFamily: FoodFamilies,
                           name: String,
                           description: String?,
                           ingredients: ArrayList<String>,
                           price: Array<Float>,
                           type: FoodType,
                           enrichable: Boolean,
                           aggiunte: ArrayList<AggiunteEntry>
) : Serializable {
    //region Properties
    val _menuItemImageIdx: FoodFamilies
    val _menuItemName: String
    val _menuItemDescription: String?
    val _menuItemPrice: Array<Float>
    val _menuItemIngredients: ArrayList<String>
    val _foodType: FoodType
    //endregion

    //region Getters and Setters
    fun menuItemFamily(): FoodFamilies {
        return _menuItemImageIdx
    }
    fun menuItemName(): String {
        return _menuItemName
    }

    fun menuItemDescription(): String? {
        return _menuItemDescription
    }
     fun menuItemIngredients(): ArrayList<String> {
         return _menuItemIngredients
     }
    fun menuItemPrice(): Array<Float> {
        return _menuItemPrice
    }
    fun foodType(): FoodType {
        return _foodType
    }
    //endregion

    //region Constructors
    init {
        _menuItemImageIdx = foodFamily
        _menuItemName = name
        _menuItemDescription = description
        _menuItemIngredients = ingredients
        _menuItemPrice = price
        _foodType = type
    }
    //endregion

     //region Methods
     /** Returns the food index image of this menu item.
      *@return      the index image _menuItemImageIdx
      */
     fun getResourceImage(): Int {
         return when (_menuItemImageIdx) {
             FoodFamilies.PIZZA_NAPOLETANA -> R.drawable.pizzenapoletane_img
             FoodFamilies.SPIANATE -> R.drawable.spianate_img
             FoodFamilies.SPIANATE_RIPIENE -> R.drawable.spianateripiene_img
             FoodFamilies.FRITTI -> R.drawable.fritti_img
         }
     }

    open fun getSizesPrices(): Array<Float> {
        return emptyArray()
    }

    open fun getSizesString(sizes: FoodSizes): String {
        return ""
    }

    open fun getSizesValues(): ArrayList<FoodSizes> {
        return arrayListOf()
    }

    open fun getCurrentSize(): FoodSizes? {
        return null
    }

    open fun getCurrentPrice(): Float {
        return 0.0f
    }

    open fun increaseCurrSize() {}

    open fun getCurrentPieces(): Pair<PiecesSizes, Int>? {
        return null
    }

    open fun increaseCurrPieces() {}

    /**
     *
     */
    open fun getFoodType(): FoodType {
        return _foodType
    }

    /** Returns the available sizes of the menu item.
      * If it's null, then no sizes are available
      * @return     the value of the available sizes
      */
    open fun getTaglie(): ArrayList<FoodSizes>? {
         return null
     }

    /** Returns the available pieces of the menu item.
     * If it's null, then no sizes are available
     * @return     the value of the available sizes
     */
    open fun getPieces(): ArrayList<Pair<PiecesSizes, Int>> {
        return arrayListOf()
    }

    /** Return the available addable foods to enrich the order.
     *  If it's null, then no items can be added
     */
    open fun getEnricheables(): ArrayList<AggiunteEntry>? {
        return null
    }

    /** Return the number of addable foods to enrich the order.
     *  If it's null, then no items have been added to the food
     */
    open fun getAggiunte(): ArrayList<AggiuntaType>? {
        return null
    }
    //endregion
}