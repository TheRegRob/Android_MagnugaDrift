package com.example.magnugadrift.classes.Menu.Enums

enum class PiecesSizes(value: Int) {
    CUT_1(0),
    CUT_2(1),
    CUT_3(2),
    CUT_4(3),
    CUT_5(4),
    CUT_6(5);

    private var value = 0
    init {
        this.value = value
    }

    open fun getValue(): Int {
        return value
    }
}