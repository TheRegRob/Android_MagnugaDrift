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
                name = "Verdure"
                price = arrayListOf(0.50f, 0.70f, 1.00f)
            }
            AggiunteEntry.PZ_AGGIUNTA_DI_PROSCIUTTO -> {
                name = "Prosciutto"
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
                name = "Doppia carne"
                price = arrayListOf(1.50f)
            }
            AggiunteEntry.HM_DOPPIA_CARNE_MAXI -> {
                name = "Doppia carne maxi"
                price = arrayListOf(5.00f)
            }
            AggiunteEntry.HM_DOPPIA_CARNE_GIGA -> {
                name = "Doppia carne giga"
                price = arrayListOf(6.50f)
            }
            AggiunteEntry.FC_AGGIUNTA_VERDURE -> {
                name = "Verdure"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.FC_AGGIUNTA_FORMAGGI -> {
                name = "Formaggi"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.FC_AGGIUNTA_SALSE -> {
                name = "Salse"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.FC_AGGIUNTA_SALSICCIA -> {
                name = "Salsiccia"
                price = arrayListOf(1.20f)
            }
            AggiunteEntry.FC_AGGIUNTA_AFFETTATI -> {
                name = "Affettati"
                price = arrayListOf(1.20f)
            }
            AggiunteEntry.SL_AGGIUNTE -> {
                name = "Aggiunte"
                price = arrayListOf(0.70f)
            }
            AggiunteEntry.SL_AGGIUNTA_PIADINA -> {
                name = "Piadina"
                price = arrayListOf(1.10f)
            }
            AggiunteEntry.SL_AGGIUNTA_SPIANATA -> {
                name = "Spianata"
                price = arrayListOf(1.20f)
            }
        }
    }

}