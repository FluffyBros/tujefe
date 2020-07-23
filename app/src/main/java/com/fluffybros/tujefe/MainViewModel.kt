package com.fluffybros.tujefe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fluffybros.tujefe.db.HomeRecyclerDatabase
import com.fluffybros.tujefe.db.HomeRecyclerItem
import com.fluffybros.tujefe.db.HomeRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _homeList = MutableLiveData<List<HomeRecyclerItem>>(listOf())
    private val repository : HomeRepository
    val homeList: LiveData<List<HomeRecyclerItem>>

    init {
        val homeRecyclerDao = HomeRecyclerDatabase.getDatabase(application).homeRecyclerDao()
        repository = HomeRepository(homeRecyclerDao)
        homeList = repository.allHomeRecyclerItems
    }

    fun addRecyclerItem(name: String, code: String) {
        val newItem = HomeRecyclerItem(
            _homeList.value?.size ?: 0,
            name,
            code
        )
        _homeList.value = _homeList.value?.plus(newItem)
    }
}
