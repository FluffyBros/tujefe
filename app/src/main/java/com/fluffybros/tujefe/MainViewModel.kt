package com.fluffybros.tujefe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluffybros.tujefe.ui.home.HomeRecyclerItem

class MainViewModel : ViewModel() {
    private val _homeList = MutableLiveData<ArrayList<HomeRecyclerItem>>().apply{
        value = generateHomeRecyclerItems()
    }

    val homeList: LiveData<ArrayList<HomeRecyclerItem>> = _homeList

    private fun generateHomeRecyclerItems(): ArrayList<HomeRecyclerItem> {
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