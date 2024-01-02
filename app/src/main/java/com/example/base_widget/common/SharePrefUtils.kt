package com.example.base_widget.common

import android.content.Context
import android.content.SharedPreferences


object SharePrefUtils {
    private const val PREFERENCES_NAME = "IOS_LAUNCHER"
    private var sharePref: SharedPreferences? = null
    const val IS_RATED = "IS_RATED"
    const val WALLPAPER = "WALLPAPER"
    const val LIST_APP_KID_ZONE = "list app kid zone"

    fun init(context: Context) {
        if (sharePref == null) {
            sharePref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        }
    }

    fun setValue(keyWord: String, value: Any?) {
        val editor = sharePref?.edit()
        when (value) {
            is Int -> editor?.putInt(keyWord, value)
            is Float -> editor?.putFloat(keyWord, value)
            is Long -> editor?.putLong(keyWord, value)
            is Boolean -> editor?.putBoolean(keyWord, value)
            is String -> editor?.putString(keyWord, value)
        }
        editor?.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(keyWord: String, defaultValue: T): T {
        return when (defaultValue) {
            is Int -> (sharePref?.getInt(keyWord, defaultValue) ?: defaultValue) as T
            is Long -> (sharePref?.getLong(keyWord, defaultValue) ?: defaultValue) as T
            is Float -> (sharePref?.getFloat(keyWord, defaultValue) ?: defaultValue) as T
            is Boolean -> (sharePref?.getBoolean(keyWord, defaultValue) ?: defaultValue) as T
            is String -> (sharePref?.getString(keyWord, defaultValue) ?: defaultValue) as T
            else -> return defaultValue
        }
    }

    var language: String
        get() = getValue("language", "en")
        set(value) = setValue("language", value)

    var isShowLanguage: Boolean
        get() = getValue("isShowLanguage", true)
        set(value) = setValue("isShowLanguage", value)

    var isShowIntro: Boolean
        get() = getValue("isShowIntro", true)
        set(value) = setValue("isShowIntro", value)
    var isShowInterIntro: Boolean
        get() = getValue("isShowInterIntro", true)
        set(value) = setValue("isShowInterIntro", value)
    var isRated: Boolean
        get() = getValue("isRated", true)
        set(value) = setValue("isRated", value)

    var wallpaper: Int
        get() = getValue(WALLPAPER, 0)
        set(value) = setValue(WALLPAPER, value)


}