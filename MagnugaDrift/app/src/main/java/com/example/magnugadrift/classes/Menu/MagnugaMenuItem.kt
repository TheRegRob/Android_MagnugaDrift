package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes

open class MagnugaMenuItem(imgResId: FoodImages,
                               name: String,
                               ingredients: ArrayList<String>,
                               price: Array<Float>,
                               type: FoodType,
                                enrichable: Boolean,
                                aggiunte: ArrayList<AggiunteEntry>
) {
    //region Properties
    val _menuItemImageIdx: FoodImages
    val _menuItemName: String
    val _menuItemPrice: Array<Float>
    val _menuItemIngredients: ArrayList<String>
    val _foodType: FoodType
    //endregion

    //region Getters and Setters
    fun menuItemImage(): FoodImages {
        return _menuItemImageIdx
    }
    fun menuItemName(): String {
        return _menuItemName
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
        _menuItemImageIdx = imgResId
        _menuItemName = name
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
         when (_menuItemImageIdx) {
             FoodImages.PIZZA_NAPOLETANA -> return R.drawable.pizzenapoletane_img
             FoodImages.SPIANATE -> return R.drawable.spianate_img
             FoodImages.SPIANATE_RIPIENE -> return R.drawable.spianateripiene_img
         }
     }

    open fun getCurrentSize(): PizzaSizes? {
        return null
    }

    open fun increaseCurrSize() {}

    open fun getCurrentPieces(): Int? {
        return null
    }

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
    open fun getTaglie(): ArrayList<PizzaSizes>? {
         return null
     }

    /** Returns the available pieces of the menu item.
     * If it's null, then no sizes are available
     * @return     the value of the available sizes
     */
    open fun getPieces(): ArrayList<Int>? {
        return null
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
    open fun getAggiunte(): ArrayList<AggiunteEntry>? {
        return null
    }
    //endregion
}