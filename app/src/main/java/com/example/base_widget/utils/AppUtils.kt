package com.example.base_widget.utils

import com.example.base_widget.R
import com.example.base_widget.ui.shop.ItemCommon
import com.example.base_widget.ui.shop.ItemTraining


object AppUtils {
    const val PRIVACY = "https://lg.taurusplay.store/privacy-policy"
    const val BASE_ICON_URL = "https://www.gstatic.com/android/keyboard/emojikitchen/"

    fun getItemPetShop(): ArrayList<ItemTraining> {
        return arrayListOf(
            ItemTraining(R.drawable.ic_pet_egg_one,"pet"),
            ItemTraining(R.drawable.ic_pet_egg_two,"pet"),
            ItemTraining(R.drawable.ic_pet_egg_three,"pet"),
        )
    }

    fun getItemPetFood(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_food_one,"chicken"),
            ItemCommon(R.drawable.ic_food_two,"sushi"),
            ItemCommon(R.drawable.ic_food_three, "milk"),
        )
    }

    fun getItemPetToilet(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_toilet_one,"bathtub"),
            ItemCommon(R.drawable.ic_toilet_two,"paper"),
            ItemCommon(R.drawable.ic_toilet_three,"soap"),
        )
    }

    fun getItemPetSleep(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_sleep_one,"bear"),
        )
    }

    fun getItemDetailsPlant(): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_details_plant_one,"sprinklers"),
            ItemCommon(R.drawable.ic_details_plant_four,"cut"),
            ItemCommon(R.drawable.ic_details_plant_two,"fertilize"),
            ItemCommon(R.drawable.ic_details_plant_three,"sunlight"),
        )
    }

    fun getItemPlantShop(): ArrayList<ItemTraining> {
        return arrayListOf(
            ItemTraining(R.drawable.ic_plant_one,"plant"),
            ItemTraining(R.drawable.ic_plant_two,"plant"),
            ItemTraining(R.drawable.ic_plant_three,"plant"),
        )
    }

}