package com.example.base_widget.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.base_widget.R
import com.example.base_widget.common.enable
import com.example.base_widget.common.hide
import com.example.base_widget.common.show
import com.example.base_widget.databinding.ItemDetailsPetPlantBinding
import com.example.base_widget.ui.shop.ItemCommon
import com.example.base_widget.ui.shop.ItemTraining

object BaseConfig {
    const val EXPERIENCE = 25
    const val DEFAULT_VALUE = 0
    const val LEVEL_1_EXPERIENCE = 100
    const val LEVEL_2_EXPERIENCE = 150
    const val LEVEL_3_EXPERIENCE = 200
    const val LEVEL_4_EXPERIENCE = 250

    const val DURATION = 500L
    const val DURATION_PROGRESS_CHANGE = 505L

    const val FIRST_TIME_STAMP_PET1 = "FIRST_TIME_STAMP_PET1"
    const val CURRENT_TIME_STAMP_PET1 = "CURRENT_TIME_STAMP_PET1"
    const val LATEST_TIME_STAMP_PET1 = "LATEST_TIME_STAMP_PET1"
    const val TIME_STAMP_PET1 = "TIME_STAMP_PET1"
    const val FIRST_TIME_STAMP_PET2 = "FIRST_TIME_STAMP_PET2"
    const val CURRENT_TIME_STAMP_PET2 = "CURRENT_TIME_STAMP_PET2"
    const val LATEST_TIME_STAMP_PET2 = "LATEST_TIME_STAMP_PET2"
    const val TIME_STAMP_PET2 = "TIME_STAMP_PET2"
    const val FIRST_TIME_STAMP_PET3 = "FIRST_TIME_STAMP_PET3"
    const val CURRENT_TIME_STAMP_PET3 = "CURRENT_TIME_STAMP_PET3"
    const val LATEST_TIME_STAMP_PET3 = "LATEST_TIME_STAMP_PET3"
    const val TIME_STAMP_PET3 = "TIME_STAMP_PET3"
    const val FIRST_TIME_STAMP_PET4 = "FIRST_TIME_STAMP_PET4"
    const val CURRENT_TIME_STAMP_PET4 = "CURRENT_TIME_STAMP_PET4"
    const val LATEST_TIME_STAMP_PET4 = "LATEST_TIME_STAMP_PET4"
    const val TIME_STAMP_PET4 = "TIME_STAMP_PET4"

    const val FIRST_TIME_STAMP_PLANT1 = "FIRST_TIME_STAMP_PLANT1"
    const val CURRENT_TIME_STAMP_PLANT1 = "CURRENT_TIME_STAMP_PLANT1"
    const val LATEST_TIME_STAMP_PLANT1 = "LATEST_TIME_STAMP_PLANT1"
    const val TIME_STAMP_PLANT1 = "TIME_STAMP_PLANT1"
    const val FIRST_TIME_STAMP_PLANT2 = "FIRST_TIME_STAMP_PLANT2"
    const val CURRENT_TIME_STAMP_PLANT2 = "CURRENT_TIME_STAMP_PLANT2"
    const val LATEST_TIME_STAMP_PLANT2 = "LATEST_TIME_STAMP_PLANT2"
    const val TIME_STAMP_PLANT2 = "TIME_STAMP_PLANT2"
    const val FIRST_TIME_STAMP_PLANT3 = "FIRST_TIME_STAMP_PLANT3"
    const val CURRENT_TIME_STAMP_PLANT3 = "CURRENT_TIME_STAMP_PLANT3"
    const val LATEST_TIME_STAMP_PLANT3 = "LATEST_TIME_STAMP_PLANT3"
    const val TIME_STAMP_PLANT3 = "TIME_STAMP_PLANT3"
    const val FIRST_TIME_STAMP_PLANT4 = "FIRST_TIME_STAMP_PLANT4"
    const val CURRENT_TIME_STAMP_PLANT4 = "CURRENT_TIME_STAMP_PLANT4"
    const val LATEST_TIME_STAMP_PLANT4 = "LATEST_TIME_STAMP_PLANT4"
    const val TIME_STAMP_PLANT4 = "TIME_STAMP_PLANT4"




    const val PET_DETAILS = "PET_DETAILS"
    const val PLANT_DETAILS = "PLANT_DETAILS"

    const val ADD_WIDGET = "ADD_WIDGET"
    const val PET = "PET"
    const val PLANT = "PLANT"

    const val UPDATE_LIST = "UPDATE_LIST"
    const val UPDATE = "UPDATE"

    const val ITEM_SELECT = "ITEM_SELECT"



    fun getGifByPos(context: Context,pos: Int,imageView: ImageView,isPet: Boolean,type: Int)
    {
        val gifPetArray = when(type)
        {
            0 -> intArrayOf(
                R.drawable.egg_animation,
                R.drawable.shower_cat,
                R.drawable.wc_cat,
                R.drawable.sleep_cat,
            )

            1 -> intArrayOf(
                R.drawable.egg_animation,
                R.drawable.shower_dog,
                R.drawable.wc_dog,
                R.drawable.sleep_dog,
            )

            else -> intArrayOf(
                R.drawable.egg_animation,
                R.drawable.shower_rabbit,
                R.drawable.wc_rabbit,
                R.drawable.sleep_rabbit,
            )

        }

        val gifPlantArray = when(type)
        {
            0 -> intArrayOf(
                R.drawable.egg_animation,
                R.drawable.shower_cat,
                R.drawable.wc_cat,
                R.drawable.sleep_cat,
            )

            1 -> intArrayOf(
                R.drawable.egg_animation,
                R.drawable.shower_cat,
                R.drawable.wc_cat,
                R.drawable.sleep_cat,
            )

            else -> intArrayOf(
                R.drawable.egg_animation,
                R.drawable.shower_cat,
                R.drawable.wc_cat,
                R.drawable.sleep_cat,
            )

        }

        if (isPet)
        {
            if (pos in gifPetArray.indices) {
                Glide.with(context)
                    .asGif()
                    .centerCrop()
                    .load(gifPetArray[pos])
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            } else {
                Glide.with(context)
                    .load(R.drawable.iv_cat)
                    .into(imageView)
            }
        }
        else
        {
            if (pos in gifPlantArray.indices) {
                Glide.with(context)
                    .asGif()
                    .centerCrop()
                    .load(gifPlantArray[pos])
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            } else {
                Glide.with(context)
                    .load(R.drawable.iv_rose)
                    .into(imageView)
            }
        }
    }


    fun showExperienceUp(context: Context,textView: TextView,isLvMax: Boolean){
        textView.show()
        if (isLvMax)
        {
            textView.text = context.getString(R.string.tvToastLvMax)
        }
        else
        {
            textView.text = String.format(context.getString(R.string.tvExp), EXPERIENCE)
        }

        val animatorSet = AnimatorSet()
        val translationY = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, 0f, -300f)
        translationY.interpolator = AccelerateInterpolator()
        val alpha = ObjectAnimator.ofFloat(textView, View.ALPHA, 1f, 0f)
        animatorSet.play(translationY).with(alpha)
        animatorSet.duration = DURATION
        translationY.duration = (DURATION / 2)
        alpha.duration = (DURATION / 2)
        animatorSet.start()

        Handler(Looper.getMainLooper()).postDelayed({
            textView.hide()
        }, DURATION)
    }
    fun getItemPetShop(): ArrayList<ItemTraining> {
        return arrayListOf(
            ItemTraining(R.drawable.ic_pet_egg_one,PET),
            ItemTraining(R.drawable.ic_pet_egg_two,PET),
            ItemTraining(R.drawable.ic_pet_egg_three,PET),
        )
    }

    fun getItemPlant(context: Context): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_water_the_tree,context.getString(R.string.ct_plant_1),2.0),
            ItemCommon(R.drawable.ic_prune,context.getString(R.string.ct_plant_2),3.0),
            ItemCommon(R.drawable.ic_sun,context.getString(R.string.ct_plant_3),4.0),
            ItemCommon(R.drawable.ic_fertilize,context.getString(R.string.ct_plant_4),1.0),
        )
    }
    fun getItemPet(context: Context): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_eat,context.getString(R.string.ct_pet_1),0.05),
            ItemCommon(R.drawable.ic_shower,context.getString(R.string.ct_pet_2),0.1),
            ItemCommon(R.drawable.ic_toilet,context.getString(R.string.ct_pet_3),0.05),
            ItemCommon(R.drawable.ic_sleep,context.getString(R.string.ct_pet_4),0.1),
        )
    }

    fun getItemPlantShop(): ArrayList<ItemTraining> {
        return arrayListOf(
            ItemTraining(R.drawable.ic_plant_one, PLANT),
            ItemTraining(R.drawable.ic_plant_two,PLANT),
            ItemTraining(R.drawable.ic_plant_three,PLANT),
        )
    }

    fun hoursToMilliseconds(hours: Double): Long {
        return (hours * 60 * 60 * 1000).toLong()
    }

}