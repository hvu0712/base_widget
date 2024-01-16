package com.example.base_widget.utils

import android.content.Context
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

    fun getItemPlant(context: Context): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_details_plant_one,context.getString(R.string.ct_plant_1)),
            ItemCommon(R.drawable.ic_details_plant_four,context.getString(R.string.ct_plant_2)),
            ItemCommon(R.drawable.ic_details_plant_three,context.getString(R.string.ct_plant_3)),
            ItemCommon(R.drawable.ic_details_plant_two,context.getString(R.string.ct_plant_4)),
        )
    }
    fun getItemPet(context: Context): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_food_one,context.getString(R.string.ct_pet_1)),
            ItemCommon(R.drawable.ic_toilet_one,context.getString(R.string.ct_pet_2)),
            ItemCommon(R.drawable.ic_toilet,context.getString(R.string.ct_pet_3)),
            ItemCommon(R.drawable.ic_sleep_one,context.getString(R.string.ct_pet_4)),
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