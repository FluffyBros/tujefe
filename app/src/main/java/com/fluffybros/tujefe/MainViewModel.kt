package com.fluffybros.tujefe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluffybros.tujefe.ui.home.HomeRecyclerItem

class MainViewModel : ViewModel() {
    private val _homeList = MutableLiveData<List<HomeRecyclerItem>>().apply{
        value = generateHomeRecyclerItems()
    }

    private fun generateHomeRecyclerItems(): List<HomeRecyclerItem> {
        // This is a dummy list
        // TODO: Replace with functionality for retrieving items from a database
        val list = ArrayList<HomeRecyclerItem>()
        for (i in 0 until 100) {
            val item = HomeRecyclerItem(
                i,
                "Account $i",
                "000-000"
            )
            list += item
        }
        return list
    }
}