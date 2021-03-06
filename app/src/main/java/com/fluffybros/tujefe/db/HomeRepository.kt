package com.fluffybros.tujefe.db

import androidx.lifecycle.LiveData

class HomeRepository(private val homeRecyclerDao: HomeRecyclerDao) {

    val allHomeRecyclerItems: LiveData<List<HomeRecyclerItem>> = homeRecyclerDao.getAlphabetizedWords()

    suspend fun insert(homeRecyclerItem: HomeRecyclerItem) {
        homeRecyclerDao.insert(homeRecyclerItem)
    }

    suspend fun delete(homeRecyclerItem: HomeRecyclerItem) {
        homeRecyclerDao.delete(homeRecyclerItem)
    }

    suspend fun rename(name: String, id: Int) {
        homeRecyclerDao.rename(name, id)
    }
}
