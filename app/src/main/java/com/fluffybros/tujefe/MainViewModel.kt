package com.fluffybros.tujefe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fluffybros.tujefe.db.HomeRecyclerDatabase
import com.fluffybros.tujefe.db.HomeRecyclerItem
import com.fluffybros.tujefe.db.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HomeRepository
    val homeList: LiveData<List<HomeRecyclerItem>>

    init {
        val homeRecyclerDao = HomeRecyclerDatabase.getDatabase(application).homeRecyclerDao()
        repository = HomeRepository(homeRecyclerDao)
        homeList = repository.allHomeRecyclerItems
    }

    fun addRecyclerItem(name: String, code: String) {
        val newItem = HomeRecyclerItem(
            homeList.value?.size ?: 0,
            name,
            code
        )
        insert(newItem)
    }

    private fun insert(newItem: HomeRecyclerItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(newItem)
    }

    fun delete(homeRecyclerItem: HomeRecyclerItem) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(homeRecyclerItem)
    }
}
