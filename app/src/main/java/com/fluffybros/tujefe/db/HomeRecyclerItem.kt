package com.fluffybros.tujefe.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_recycler_item")
data class HomeRecyclerItem(@PrimaryKey val id: Int, var name: String, var secret: String, var code: String, var countMax: Int, var count: Int)
