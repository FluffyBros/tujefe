package com.fluffybros.tujefe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [AccountTable::class], version = 1, exportSchema = false)
public abstract class AccountDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDAO

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "accountDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
