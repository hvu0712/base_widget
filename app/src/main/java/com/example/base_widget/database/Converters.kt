package com.example.base_widget.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): MutableList<String> {
        if (value.isEmpty()) return mutableListOf()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(value: MutableList<String>): String {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().toJson(value, type)
    }
}