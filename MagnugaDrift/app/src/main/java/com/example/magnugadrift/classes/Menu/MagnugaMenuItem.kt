package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Menu.Enums.FoodType

 open class MagnugaMenuItem(imgResId: FoodImages,
                               name: String,
                               ingredients: ArrayList<String>,
                               price: Array<Float>,
                               type: FoodType
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
     fun getResourceImage(): Int {
         when (_menuItemImageIdx) {
             FoodImages.PIZZA_NAPOLETANA -> return R.drawable.pizzenapoletane_img
             FoodImages.SPIANATE -> return R.drawable.spianate_img
             FoodImages.SPIANATE_RIPIENE -> return R.drawable.spianateripiene_img
         }
     }
     //endregion
}