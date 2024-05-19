package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Enrich.isEnrichable
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes

class PizzaNapoletanaMI(name: String,
                        descrizione: String?,
                        price: Array<Float>,
                        type: FoodType,
                        ingredients: ArrayList<String>?,
                        sizes: ArrayList<FoodSizes>?) :
                        MagnugaMenuItem(FoodFamilies.PIZZA_NAPOLETANA, name, descrizione,
                            ingredients, price, type),
                        isEnrichable {
    //region Properties
    private val _ingredients: ArrayList<String>?
    private val _sizes: ArrayList<FoodSizes>?
    private val _prices: Array<Float>
    private val _enricheables: ArrayList<AggiunteEntry>
    private val _aggiunte: ArrayList<AggiuntaType>?
    private var _curSize: FoodSizes?
    //endregion

    //region Getters and Setters
    fun getIngredients(): ArrayList<String>? {
        return _ingredients
    }
    override fun getSizesValues(): ArrayList<FoodSizes>? {
        return _sizes
    }
    override fun getSizesPrices(): Array<Float> {
        return _prices
    }
    //endregion

    //region Constructors
    init {
        _ingredients = ingredients
        _sizes = sizes
        _prices = price
        _aggiunte = ArrayList()
        _curSize = sizes?.get(0)
        _enricheables = arrayListOf(
            AggiunteEntry.PZ_AGGIUNTA_DI_PROSCIUTTO,
            AggiunteEntry.PZ_BURRATA,
            AggiunteEntry.PZ_IMPASTO_DI_KAMUT,
            AggiunteEntry.PZ_DOPPIA_MOZZARELLA,
            AggiunteEntry.PZ_MOZZARELLA_BUFALA)

    }
    //endregion

    //region Methods and functions
    override fun getCurrentSize(): FoodSizes? {
        return _curSize
    }

    override fun increaseCurrSize() {
        _curSize = if (_curSize == _sizes?.last()) {
            _sizes?.get(0)
        } else {
            _sizes?.get(_sizes.indexOf(_curSize) + 1)
        }
    }

    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getEnricheables(): ArrayList<AggiunteEntry> {
        return _enricheables
    }

    override fun getAggiunte(): ArrayList<AggiuntaType>? {
        return _aggiunte
    }

    override fun getSizesString(sizes: FoodSizes): String {
        return when (sizes) {
            FoodSizes.S -> "Piccola"
            FoodSizes.M -> "Media"
            FoodSizes.L -> "Maxi"
        }
    }

    override fun getCurrentPrice(): Float {
        when (_curSize) {
            FoodSizes.S -> return _prices[0]
            FoodSizes.M -> return _prices[1]
            FoodSizes.L -> return _prices[2]
            else -> {
                return 0.0f
            }
        }
    }
    //endregion
}