package com.example.base_widget.common

object RemoteConfig {
    const val DISPLAY_LANGUAGE = "display_language"
    const val DISPLAY_INTRO = "display_intro"

    var isShowLanguage: Boolean
        get() = SharePrefUtils.getValue(DISPLAY_LANGUAGE, false)
        set(value) = SharePrefUtils.setValue(DISPLAY_LANGUAGE, value)
    var isShowIntro: Boolean
        get() = SharePrefUtils.getValue(DISPLAY_INTRO, true)
        set(value) = SharePrefUtils.setValue(DISPLAY_INTRO, value)
}