package com.example.magnugadrift.classes.Menu

import com.example.magnugadrift.classes.AggiuntaType
import com.example.magnugadrift.classes.Enrich.isEnrichable
import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry
import com.example.magnugadrift.classes.Menu.Enums.FoodType
import com.example.magnugadrift.classes.Menu.Enums.PizzaSizes

class PizzaNapoletanaMI(name: String,
                        price: Array<Float>,
                        type: FoodType,
                        ingredients: ArrayList<String>,
                        sizes: ArrayList<PizzaSizes>) :
                        MagnugaMenuItem(FoodImages.PIZZA_NAPOLETANA, name, ingredients, price, type,
                            true, arrayListOf()),
                        isEnrichable {
    //region Properties
    private val _ingredients: ArrayList<String>
    private val _sizes: ArrayList<PizzaSizes>
    private val _enricheables: ArrayList<AggiunteEntry>
    private val _aggiunte: ArrayList<AggiunteEntry>
    private var _curSize: PizzaSizes
    //endregion

    //region Getters and Setters
    fun pizzaNapoletanaIngredients(): ArrayList<String> {
        return _ingredients
    }
    fun pizzaNapoletanaSizes(): ArrayList<PizzaSizes> {
        return _sizes
    }
    //endregion

    //region Constructors
    init {
        _ingredients = ingredients
        _sizes = sizes
        _aggiunte = ArrayList<AggiunteEntry>()
        _curSize = sizes[0]
        _enricheables = arrayListOf(
            AggiunteEntry.PZ_AGGIUNTA_DI_PROSCIUTTO,
            AggiunteEntry.PZ_BURRATA,
            AggiunteEntry.PZ_IMPASTO_DI_KAMUT,
            AggiunteEntry.PZ_DOPPIA_MOZZARELLA,
            AggiunteEntry.PZ_MOZZARELLA_BUFALA)

    }
    //endregion

    //region Methods and functions
    override fun getCurrentSize(): PizzaSizes? {
        return _curSize
    }

    override fun increaseCurrSize() {
        _curSize = if (_curSize == _sizes.last()) {
            _sizes[0]
        } else {
            _sizes[_sizes.indexOf(_curSize) + 1]
        }
    }

    override fun getFoodType(): FoodType {
        return _foodType
    }

    override fun getTaglie(): ArrayList<PizzaSizes> {
        return _sizes
    }

    override fun getEnricheables(): ArrayList<AggiunteEntry> {
        return _enricheables
    }

    override fun getAggiunte(): ArrayList<AggiunteEntry> {
        return _aggiunte
    }
    //endregion
}