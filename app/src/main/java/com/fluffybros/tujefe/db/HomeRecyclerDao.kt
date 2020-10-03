package com.fluffybros.tujefe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HomeRecyclerDao {

    @Query("SELECT * from home_recycler_item ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<HomeRecyclerItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(homeRecyclerItem: HomeRecyclerItem)

    @Delete
    suspend fun delete(homeRecyclerItem: HomeRecyclerItem)

    @Query("UPDATE home_recycler_item SET name = :name WHERE id = :id")
    suspend fun rename(name: String, id: Int)

    @Query("UPDATE home_recycler_item SET code = :newCode WHERE id = :id")
    suspend fun updateCode(newCode: String, id: Int)

    @Query("DELETE FROM home_recycler_item")
    suspend fun deleteAll()
}
