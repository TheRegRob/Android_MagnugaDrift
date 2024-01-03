package com.example.magnugadrift.ui.order

import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    //region Properties
    private val orderList = mutableListOf<String>()
    //endregion

    //region Methods
    fun addMenuItem(str: String) {
        orderList.add(str)
    }
    fun getOrders(): List<String> {
        return orderList.toList()
    }
    //endregion

}