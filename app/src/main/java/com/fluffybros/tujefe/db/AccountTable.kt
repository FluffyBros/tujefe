package com.fluffybros.tujefe.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accountTable")
data class AccountTable(@PrimaryKey(autoGenerate = true) val id:Int, val name:String, val code:String)