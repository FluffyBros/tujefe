package com.fluffybros.tujefe.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.TypeConverter

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromLiveDataString(data: MutableLiveData<String>): String {
            return data.value ?: ""
        }

        @TypeConverter
        @JvmStatic
        fun toLiveDataString(str: String): MutableLiveData<String> {
            val data = MutableLiveData<String>()
            data.value = str
            return data
        }

        fun fromLiveDataInt(data: MutableLiveData<Int>): Int {
            return data.value ?: -100000
        }

        @TypeConverter
        @JvmStatic
        fun toLiveDataInt(num: Int): MutableLiveData<Int> {
            val data = MutableLiveData<Int>()
            data.value = num
            return data
        }
    }
}