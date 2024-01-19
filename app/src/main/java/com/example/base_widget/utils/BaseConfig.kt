package com.example.base_widget.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.base_widget.R
import com.example.base_widget.common.hide
import com.example.base_widget.common.show
import com.example.base_widget.ui.shop.ItemCommon
import com.example.base_widget.ui.shop.ItemTraining

object BaseConfig {
    const val EXPERIENCE = 25
    const val DEFAULT_VALUE = 0
    const val LEVEL_1_EXPERIENCE = 100
    const val LEVEL_2_EXPERIENCE = 150
    const val LEVEL_3_EXPERIENCE = 200
    const val LEVEL_4_EXPERIENCE = 250
    const val LEVEL_MAX_EXPERIENCE = 500

    const val DURATION = 500L



    const val PET_DETAILS = "PET_DETAILS"
    const val PLANT_DETAILS = "PLANT_DETAILS"

    const val ADD_WIDGET = "ADD_WIDGET"
    const val PET = "PET"
    const val PLANT = "PLANT"

    const val UPDATE_LIST = "UPDATE_LIST"
    const val UPDATE = "UPDATE"

    const val ITEM_SELECT = "ITEM_SELECT"



    fun getGifByPos(context: Context,pos: Int,imageView: ImageView,isPet: Boolean)
    {
        val gifPetArray = intArrayOf(
            R.drawable.egg_animation,
            R.drawable.egg_animation,
            R.drawable.egg_animation,
            R.drawable.egg_animation,
        )

        val gifPlantArray = intArrayOf(
            R.drawable.plant_animation,
            R.drawable.plant_animation,
            R.drawable.plant_animation,
            R.drawable.plant_animation,
        )

        if (isPet)
        {
            if (pos in gifPetArray.indices) {
                Glide.with(context)
                    .asGif()
                    .centerCrop()
                    .placeholder(R.drawable.iv_plant)
                    .load(gifPetArray[pos])
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            } else {
                Glide.with(context)
                    .load(R.drawable.plant_animation)
                    .into(imageView)
            }
        }
        else
        {
            if (pos in gifPlantArray.indices) {
                Glide.with(context)
                    .asGif()
                    .centerCrop()
                    .placeholder(R.drawable.iv_plant)
                    .load(gifPlantArray[pos])
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            } else {
                Glide.with(context)
                    .load(R.drawable.plant_animation)
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

        var animatorSet = AnimatorSet()
        var translationY = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, 0f, -300f)
        translationY.interpolator = AccelerateInterpolator()
        var alpha = ObjectAnimator.ofFloat(textView, View.ALPHA, 1f, 0f)
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
            ItemCommon(R.drawable.ic_water_the_tree,context.getString(R.string.ct_plant_1),60000L),
            ItemCommon(R.drawable.ic_prune,context.getString(R.string.ct_plant_2),60000L),
            ItemCommon(R.drawable.ic_sun,context.getString(R.string.ct_plant_3),60000L),
            ItemCommon(R.drawable.ic_fertilize,context.getString(R.string.ct_plant_4),60000L),
        )
    }
    fun getItemPet(context: Context): ArrayList<ItemCommon> {
        return arrayListOf(
            ItemCommon(R.drawable.ic_eat,context.getString(R.string.ct_pet_1),60000L),
            ItemCommon(R.drawable.ic_shower,context.getString(R.string.ct_pet_2),60000L),
            ItemCommon(R.drawable.ic_toilet,context.getString(R.string.ct_pet_3),60000L),
            ItemCommon(R.drawable.ic_sleep,context.getString(R.string.ct_pet_4),60000L),
        )
    }

    fun getItemPlantShop(): ArrayList<ItemTraining> {
        return arrayListOf(
            ItemTraining(R.drawable.ic_plant_one, PLANT),
            ItemTraining(R.drawable.ic_plant_two,PLANT),
            ItemTraining(R.drawable.ic_plant_three,PLANT),
        )
    }

}