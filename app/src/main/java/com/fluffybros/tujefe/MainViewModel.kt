package com.fluffybros.tujefe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluffybros.tujefe.ui.home.HomeRecyclerItem

class MainViewModel : ViewModel() {
    private val _homeList = MutableLiveData<ArrayList<HomeRecyclerItem>>().apply {
        value = ArrayList()
    }

    val homeList: LiveData<ArrayList<HomeRecyclerItem>> = _homeList

    fun addRecyclerItem(name: String, code: String) {
        val newItem = HomeRecyclerItem(_homeList.value?.size ?: 0, name, code)
        _homeList.value?.add(newItem)
    }
}
