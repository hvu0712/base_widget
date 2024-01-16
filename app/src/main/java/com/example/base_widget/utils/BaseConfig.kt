package com.example.base_widget.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.base_widget.R
import com.example.base_widget.ui.shop.ItemCommon
import com.example.base_widget.ui.shop.ItemTraining
import java.text.FieldPosition

object BaseConfig {
    const val EXPERIENCE = 25
    const val DEFAULT_VALUE = 0
    const val LEVEL_1_EXPERIENCE = 100
    const val LEVEL_2_EXPERIENCE = 150
    const val LEVEL_3_EXPERIENCE = 200
    const val LEVEL_4_EXPERIENCE = 250

    fun getGifByPos(context: Context,pos: Int,imageView: ImageView)
    {
        val gifArray = intArrayOf(
            R.drawable.plant_animation,
            R.drawable.plant_animation,
            R.drawable.plant_animation,
            R.drawable.plant_animation,
        )

        // Kiểm tra xem vị trí có hợp lệ không
        if (pos in 0 until gifArray.size) {
            Glide.with(context)
                .asGif()
                .centerCrop()
                .placeholder(R.drawable.iv_plant) // Ảnh placeholder
                .load(gifArray[pos])
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        } else {
            // Nếu vị trí không hợp lệ, hiển thị ảnh placeholder hoặc ảnh mặc định khác
            Glide.with(context)
                .load(R.drawable.plant_animation)
                .into(imageView)
        }
    }
    fun getItemPetShop(): ArrayList<ItemTraining> {
        return arrayListOf(
            ItemTraining(R.drawable.ic_pet_egg_one,"pet"),
            ItemTraining(R.drawable.ic_pet_egg_two,"pet"),
            ItemTraining(R.drawable.ic_pet_egg_three,"pet"),
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