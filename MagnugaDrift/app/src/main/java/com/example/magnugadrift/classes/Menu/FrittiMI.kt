package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodSizes
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PiecesSizes

class FrittiMI(
    name: String,
    descrizione: String?,
    price: Array<Float>,
    type: FoodType,
    ingredients: ArrayList<String>?,
    sizes: ArrayList<FoodSizes>?,
    pieces: ArrayList<Pair<PiecesSizes, Int>>?
) :
    MagnugaMenuItem(FoodFamilies.FRITTI, name, descrizione, ingredients, price, type) {
    //region Properties
    private val _ingredients: ArrayList<String>?
    private val _sizes: ArrayList<FoodSizes>?
    private val _prices: Array<Float>
    private val _pieces: ArrayList<Pair<PiecesSizes, Int>>?
    private var _curSize: FoodSizes?
    private var _curPieces: Pair<PiecesSizes, Int>?
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
        _pieces = pieces
        _curSize = sizes?.get(0)
        _curPieces = pieces?.get(0)

    }
    //endregion

    //region Methods and functions
    override fun getCurrentSize(): FoodSizes? {
        return _curSize
    }

    override fun increaseCurrSize() {
        _curSize = if (_curSize == _sizes!!.last()) {
            _sizes[0]
        } else {
            _sizes[_sizes.indexOf(_curSize) + 1]
        }
    }

    override fun increaseCurrPieces()  {
        _curPieces = if (_curPieces == _pieces!!.last()) {
            _pieces[0]
        } else {
            _pieces[_pieces.indexOf(_curPieces) + 1]
        }
    }

    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getPieces(): ArrayList<Pair<PiecesSizes, Int>>? {
        return _pieces
    }

    override fun getCurrentPieces(): Pair<PiecesSizes, Int>? {
        return _curPieces
    }

    override fun getSizesString(sizes: FoodSizes): String {
        return when (sizes) {
            FoodSizes.S -> "Piccole"
            FoodSizes.M -> "Normali"
            FoodSizes.L -> "Grandi"
        }
    }

    override fun getCurrentPrice(): Float {
        return if (_sizes != null) {
            _prices[getCurrentSize()!!.getValue()]
        } else if (_pieces != null) {
            _prices[getCurrentPieces()!!.first.getValue()]
        } else {
            _prices[0]
        }
    }
    //endregion

}