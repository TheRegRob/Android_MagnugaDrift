package com.example.magnugadrift.classes.Menu.Enums

enum class FormatoType(value: Int) {
    CARNE_CLASSICA(0),
    CARNE_POLLO(1),
    CARNE_MAXI(2),
    CARNE_CICCIO(3),
    CARNE_GIGA(4);

    companion object {
        fun fromInt(value: Int) = FormatoType.values().first { it.value == value }
    }

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }

    fun getAggiuntaEntry(): AggiunteEntry {
        return when (value) {
            0 -> AggiunteEntry.HM_DOPPIA_CARNE
            1 -> AggiunteEntry.HM_DOPPIA_CARNE_POLLO
            2 -> AggiunteEntry.HM_DOPPIA_CARNE_MAXI
            3 -> AggiunteEntry.HM_DOPPIA_CARNE_CICCIO
            4 -> AggiunteEntry.HM_DOPPIA_CARNE_GIGA
            else -> AggiunteEntry.HM_DOPPIA_CARNE
        }
    }
}