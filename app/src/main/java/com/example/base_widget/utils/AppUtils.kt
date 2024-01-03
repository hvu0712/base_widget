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
            ItemCommon(R.drawable.ic_plant_one),
            ItemCommon(R.drawable.ic_plant_two),
            ItemCommon(R.drawable.ic_plant_three),
        )
    }

    fun getItemDetailsPlant(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_details_plant_one),
            ItemCommon(R.drawable.ic_details_plant_one),
            ItemCommon(R.drawable.ic_details_plant_one),
            ItemCommon(R.drawable.ic_details_plant_one),
            ItemCommon(R.drawable.ic_details_plant_one),
            ItemCommon(R.drawable.ic_details_plant_one),
        )
    }

}