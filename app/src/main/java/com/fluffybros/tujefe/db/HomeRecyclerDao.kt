package com.fluffybros.tujefe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HomeRecyclerDao {

    @Query("SELECT * from home_recycler_item ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<HomeRecyclerItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(homeRecyclerItem: HomeRecyclerItem)

    @Query("DELETE FROM home_recycler_item")
    suspend fun deleteAll()
}