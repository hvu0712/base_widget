package com.example.base_widget.common

import android.content.Context
import android.content.SharedPreferences
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PET1
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PET2
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PET3
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PET4
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PLANT1
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PLANT2
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PLANT3
import com.example.base_widget.utils.BaseConfig.CURRENT_TIME_STAMP_PLANT4
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PET1
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PET2
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PET3
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PET4
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PLANT1
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PLANT2
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PLANT3
import com.example.base_widget.utils.BaseConfig.FIRST_TIME_STAMP_PLANT4
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PET1
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PET2
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PET3
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PET4
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PLANT1
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PLANT2
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PLANT3
import com.example.base_widget.utils.BaseConfig.LATEST_TIME_STAMP_PLANT4
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PET1
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PET2
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PET3
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PET4
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PLANT1
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PLANT2
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PLANT3
import com.example.base_widget.utils.BaseConfig.TIME_STAMP_PLANT4


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

    var firstTimeStampPet1: Long
        get() = getValue(FIRST_TIME_STAMP_PET1,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PET1, value)

    var TimeStampPet1: Long
        get() = getValue(TIME_STAMP_PET1,0L)
        set(value) = setValue(TIME_STAMP_PET1, value)

    var latestTimeStampPet1: Long
        get() = getValue(LATEST_TIME_STAMP_PET1,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PET1, value)

    var currentTimeStampPet1: Long
        get() = getValue(CURRENT_TIME_STAMP_PET1,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PET1, value)
    var firstTimeStampPet2: Long
        get() = getValue(FIRST_TIME_STAMP_PET2,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PET2, value)

    var TimeStampPet2: Long
        get() = getValue(TIME_STAMP_PET2,0L)
        set(value) = setValue(TIME_STAMP_PET2, value)

    var latestTimeStampPet2: Long
        get() = getValue(LATEST_TIME_STAMP_PET2,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PET2, value)

    var currentTimeStampPet2: Long
        get() = getValue(CURRENT_TIME_STAMP_PET2,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PET2, value)
    var firstTimeStampPet3: Long
        get() = getValue(FIRST_TIME_STAMP_PET3,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PET3, value)

    var TimeStampPet3: Long
        get() = getValue(TIME_STAMP_PET3,0L)
        set(value) = setValue(TIME_STAMP_PET3, value)

    var latestTimeStampPet3: Long
        get() = getValue(LATEST_TIME_STAMP_PET3,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PET3, value)

    var currentTimeStampPet3: Long
        get() = getValue(CURRENT_TIME_STAMP_PET3,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PET3, value)

    var firstTimeStampPet4: Long
        get() = getValue(FIRST_TIME_STAMP_PET4,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PET4, value)

    var TimeStampPet4: Long
        get() = getValue(TIME_STAMP_PET4,0L)
        set(value) = setValue(TIME_STAMP_PET4, value)

    var latestTimeStampPet4: Long
        get() = getValue(LATEST_TIME_STAMP_PET4,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PET4, value)

    var currentTimeStampPet4: Long
        get() = getValue(CURRENT_TIME_STAMP_PET4,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PET4, value)
    var firstTimeStampPlant1: Long
        get() = getValue(FIRST_TIME_STAMP_PLANT1,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PLANT1, value)

    var TimeStampPlant1: Long
        get() = getValue(TIME_STAMP_PLANT1,0L)
        set(value) = setValue(TIME_STAMP_PLANT1, value)

    var latestTimeStampPlant1: Long
        get() = getValue(LATEST_TIME_STAMP_PLANT1,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PLANT1, value)

    var currentTimeStampPlant1: Long
        get() = getValue(CURRENT_TIME_STAMP_PLANT1,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PLANT1, value)
    var firstTimeStampPlant2: Long
        get() = getValue(FIRST_TIME_STAMP_PLANT2,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PLANT2, value)

    var TimeStampPlant2: Long
        get() = getValue(TIME_STAMP_PLANT2,0L)
        set(value) = setValue(TIME_STAMP_PLANT2, value)

    var latestTimeStampPlant2: Long
        get() = getValue(LATEST_TIME_STAMP_PLANT2,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PLANT2, value)

    var currentTimeStampPlant2: Long
        get() = getValue(CURRENT_TIME_STAMP_PLANT2,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PLANT2, value)
    var firstTimeStampPlant3: Long
        get() = getValue(FIRST_TIME_STAMP_PLANT3,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PLANT3, value)

    var TimeStampPlant3: Long
        get() = getValue(TIME_STAMP_PLANT3,0L)
        set(value) = setValue(TIME_STAMP_PLANT3, value)

    var latestTimeStampPlant3: Long
        get() = getValue(LATEST_TIME_STAMP_PLANT3,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PLANT3, value)

    var currentTimeStampPlant3: Long
        get() = getValue(CURRENT_TIME_STAMP_PLANT3,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PLANT3, value)

    var firstTimeStampPlant4: Long
        get() = getValue(FIRST_TIME_STAMP_PLANT4,0L)
        set(value) = setValue(FIRST_TIME_STAMP_PLANT4, value)

    var TimeStampPlant4: Long
        get() = getValue(TIME_STAMP_PLANT4,0L)
        set(value) = setValue(TIME_STAMP_PLANT4, value)

    var latestTimeStampPlant4: Long
        get() = getValue(LATEST_TIME_STAMP_PLANT4,0L)
        set(value) = setValue(LATEST_TIME_STAMP_PLANT4, value)

    var currentTimeStampPlant4: Long
        get() = getValue(CURRENT_TIME_STAMP_PLANT4,0L)
        set(value) = setValue(CURRENT_TIME_STAMP_PLANT4, value)
}