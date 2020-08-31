package com.fluffybros.tujefe

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fluffybros.tujefe.db.HomeRecyclerDatabase
import com.fluffybros.tujefe.db.HomeRecyclerItem
import com.fluffybros.tujefe.db.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@ObsoleteCoroutinesApi
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HomeRepository
    val homeList: LiveData<List<HomeRecyclerItem>>


    init {
        val homeRecyclerDao = HomeRecyclerDatabase.getDatabase(application).homeRecyclerDao()
        repository = HomeRepository(homeRecyclerDao)
        homeList = repository.allHomeRecyclerItems
        val tickerChannel = ticker(delayMillis = 1000)
        GlobalScope.launch {
            for (event in tickerChannel) {
                val currentTime = LocalDateTime.now()
                Log.d("yo", currentTime.toString())
            }
        }
    }

    fun addRecyclerItem(name: String, secret: String) {
        val newItem = HomeRecyclerItem(
            homeList.value?.size ?: 0,
            name,
            secret,
            "000-000",
            30,
            30
        )
        insert(newItem)
    }

    private fun insert(newItem: HomeRecyclerItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(newItem)
    }

    fun delete(homeRecyclerItem: HomeRecyclerItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(homeRecyclerItem)
    }

    fun rename(name: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.rename(name, id)
    }
}
