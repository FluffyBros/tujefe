package com.fluffybros.tujefe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluffybros.tujefe.ui.home.HomeRecyclerItem

class MainViewModel : ViewModel() {
    private val _homeList = MutableLiveData<List<HomeRecyclerItem>>(ArrayList())

    val homeList: LiveData<List<HomeRecyclerItem>> = _homeList

    fun addRecyclerItem(name: String, code: String) {
        val newItem = HomeRecyclerItem(_homeList.value?.size ?: 0, name, code)
        _homeList.value = _homeList.value?.plus(newItem)
    }
}
