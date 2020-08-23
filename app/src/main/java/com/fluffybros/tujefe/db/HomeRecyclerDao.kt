package com.fluffybros.tujefe.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HomeRecyclerDao {

    @Query("SELECT * from home_recycler_item ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<HomeRecyclerItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(homeRecyclerItem: HomeRecyclerItem)

    @Delete
    suspend fun delete(homeRecyclerItem: HomeRecyclerItem)

    @Update
    suspend fun edit(homeRecyclerItem: HomeRecyclerItem)

    @Query("DELETE FROM home_recycler_item")
    suspend fun deleteAll()
}
