package com.fluffybros.tujefe.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDAO {
    @Query("SELECT * from accountTable ORDER BY name ASC")
    fun getAlphabetizedWords(): List<AccountTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: AccountTable)

    @Query("DELETE FROM accountTable")
    suspend fun deleteAll()
}