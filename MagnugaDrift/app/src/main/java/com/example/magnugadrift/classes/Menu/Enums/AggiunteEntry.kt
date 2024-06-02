package com.example.magnugadrift.classes.Menu.Enums

enum class AggiunteEntry(value: Int) {
    PZ_DOPPIA_MOZZARELLA(0),
    PZ_MOZZARELLA_BUFALA(1),
    PZ_BURRATA(2),
    PZ_AGGIUNTA_DI_VERDURE(3),
    PZ_AGGIUNTA_DI_AFFETTATI(4),
    PZ_IMPASTO_DI_KAMUT(5),
    HM_AGGIUNTE(6),
    HM_DOPPIA_CARNE(7),
    HM_DOPPIA_CARNE_POLLO(8),
    HM_DOPPIA_CARNE_MAXI(9),
    HM_DOPPIA_CARNE_CICCIO(10),
    HM_DOPPIA_CARNE_GIGA(11),
    FC_AGGIUNTA_VERDURE(12),
    FC_AGGIUNTA_FORMAGGI(13),
    FC_AGGIUNTA_SALSE(14),
    FC_AGGIUNTA_SALSICCIA(15),
    FC_AGGIUNTA_AFFETTATI(16),
    SL_AGGIUNTE(17),
    SL_AGGIUNTA_PIADINA(18),
    SL_AGGIUNTA_SPIANATA(19);

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }
}