package com.fluffybros.tujefe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HomeRecyclerItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HomeRecyclerDatabase : RoomDatabase() {

    abstract fun homeRecyclerDao(): HomeRecyclerDao

    companion object {
        @Volatile
        private var INSTANCE: HomeRecyclerDatabase? = null

        fun getDatabase(context: Context): HomeRecyclerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HomeRecyclerDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
