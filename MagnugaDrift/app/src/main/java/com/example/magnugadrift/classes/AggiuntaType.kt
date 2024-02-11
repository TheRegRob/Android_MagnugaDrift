package com.example.magnugadrift.classes

import com.example.magnugadrift.classes.Menu.Enums.AggiunteEntry

class AggiuntaType(aggiuntaEntry: AggiunteEntry) {
    private val name: String
    private val price: ArrayList<Float>

    fun getName(): String {
        return name
    }

    fun getPrice(): ArrayList<Float> {
        return price
    }

    init {
        when(aggiuntaEntry) {
            AggiunteEntry.PZ_DOPPIA_MOZZARELLA -> {
                name = "Doppia mozzarella"
                price = arrayListOf(0.60f, 0.80f, 1.20f)
            }
            AggiunteEntry.PZ_MOZZARELLA_BUFALA -> {
                name = "Mozzarella di bufala"
                price = arrayListOf(0.60f, 1.10f, 1.90f)
            }
            AggiunteEntry.PZ_BURRATA -> {
                name = "Burrata"
                price = arrayListOf(0.60f, 1.10f, 1.90f)
            }
            AggiunteEntry.PZ_AGGIUNTA_DI_VERDURE -> {
                name = "Aggiunta di verdure"
                price = arrayListOf(0.50f, 0.70f, 1.00f)
            }
            AggiunteEntry.PZ_AGGIUNTA_DI_PROSCIUTTO -> {
                name = "Aggiunta di prosciutto"
                price = arrayListOf(0.70f, 1.00f, 1.30f)
            }
            AggiunteEntry.PZ_IMPASTO_DI_KAMUT -> {
                name = "Impasto di kamut"
                price = arrayListOf(0.70f, 1.00f, 1.80f)
            }
            AggiunteEntry.HM_AGGIUNTE -> {
                name = "Aggiunte"
                price = arrayListOf(0.50f)
            }
            AggiunteEntry.HM_DOPPIA_CARNE -> {
                name = "Aggiunta doppia carne"
                price = arrayListOf(1.50f)
            }
            AggiunteEntry.HM_DOPPIA_CARNE_MAXI -> {
                name = "Aggiunta doppia carne maxi"
                price = arrayListOf(5.00f)
            }
            AggiunteEntry.HM_DOPPIA_CARNE_GIGA -> {
                name = "Aggiunta doppia carne giga"
                price = arrayListOf(6.50f)
            }
            AggiunteEntry.FC_AGGIUNTA_VERDURE -> {
                name = "Aggiunta verdure"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.FC_AGGIUNTA_FORMAGGI -> {
                name = "Aggiunta formaggi"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.FC_AGGIUNTA_SALSE -> {
                name = "Aggiunta salse"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.FC_AGGIUNTA_SALSICCIA -> {
                name = "Aggiunta salsiccia"
                price = arrayListOf(1.20f)
            }
            AggiunteEntry.FC_AGGIUNTA_AFFETTATI -> {
                name = "Aggiunta affettati"
                price = arrayListOf(1.20f)
            }
            AggiunteEntry.SL_AGGIUNTE -> {
                name = "Aggiunte"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.SL_AGGIUNTA_PIADINA -> {
                name = "Aggiunta piadina"
                price = arrayListOf(1.10f)
            }
            AggiunteEntry.SL_AGGIUNTA_SPIANATA -> {
                name = "Aggiunta spianata"
                price = arrayListOf(1.20f)
            }
        }
    }

}