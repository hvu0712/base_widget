package com.example.base_widget.utils

import com.example.base_widget.R
import com.example.base_widget.ui.shop.ItemCommon


object AppUtils {
    const val PRIVACY = "https://creative.lgapps.store/privacy-policy"
    const val BASE_ICON_URL = "https://www.gstatic.com/android/keyboard/emojikitchen/"

    fun getItemPetShop(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_pet_egg_one),
            ItemCommon(R.drawable.ic_pet_egg_two),
            ItemCommon(R.drawable.ic_pet_egg_three),
        )
    }

    fun getItemPetFood(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_food_one),
            ItemCommon(R.drawable.ic_food_two),
            ItemCommon(R.drawable.ic_food_three),
        )
    }

    fun getItemPetToilet(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_toilet_one),
            ItemCommon(R.drawable.ic_toilet_two),
            ItemCommon(R.drawable.ic_toilet_three),
        )
    }

    fun getItemPetSleep(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_sleep_one),
        )
    }

    fun getItemDetailsPlant(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_details_plant_one),
            ItemCommon(R.drawable.ic_details_plant_four),
            ItemCommon(R.drawable.ic_details_plant_two),
            ItemCommon(R.drawable.ic_details_plant_three),
        )
    }

    fun getItemPlantShop(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_plant_one),
            ItemCommon(R.drawable.ic_plant_two),
            ItemCommon(R.drawable.ic_plant_three),
        )
    }

    fun getItemPlantSelect(): ArrayList<Any> {
        return arrayListOf(
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.1"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.2"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.3"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.4"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.5"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.1"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.1"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.2"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.4"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.3"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.2"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.1"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.4"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.5"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.2"),
        )
    }

    fun getItemPetSelect(): ArrayList<Any> {
        return arrayListOf(
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.2"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.3"),
            ItemSelect(R.drawable.iv_plant_all_select,"Plant","Lv.4"),
        )
    }
}