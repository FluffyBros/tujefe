package com.fluffybros.tujefe

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fluffybros.tujefe.db.HomeRecyclerDatabase
import com.fluffybros.tujefe.db.HomeRecyclerItem
import com.fluffybros.tujefe.db.HomeRepository
import com.fluffybros.tujefe.twoFA.TwoFactor
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
                for(item in homeList.value?: listOf()){
                    if(item.count.value == 0){
                        val codeArray = item.code.value?.toByteArray()
                        repository.updateCode(TwoFactor.calculateVerificationCode(codeArray, 30), item.id)
                        item.count.postValue(item.countMax)
                    } else {
                        item.count.postValue(item.count.value?.minus(1))
                    }
                }
            }
        }
    }

    fun addRecyclerItem(name: String, secret: String) {
        val newItem = HomeRecyclerItem(
            homeList.value?.size ?: 0,
            name,
            secret,
            MutableLiveData<String>(),
            30,
            MutableLiveData<Int>()
        )
        newItem.code.value = TwoFactor.calculateVerificationCode(secret.toByteArray(), 30)
        newItem.count.value = newItem.countMax
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
