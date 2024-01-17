package com.example.base_widget.ui

import com.bumptech.glide.Glide
import com.example.base_widget.R
import android.os.Bundle
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityHomeBinding
import com.example.base_widget.base.SettingsActivity
import com.example.base_widget.ui.details.PetGardenActivity
import com.example.base_widget.ui.shop.AllShopActivity
import com.example.base_widget.utils.BaseConfig.ADD_WIDGET
import com.example.base_widget.utils.BaseConfig.PET
import com.example.base_widget.utils.BaseConfig.PLANT

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun inflateViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun initView() {
        Glide.with(this@HomeActivity).asGif().centerCrop().placeholder(R.drawable.iv_plant).load(R.drawable.plant_animation).into(binding.ivPlant)
        Glide.with(this@HomeActivity).asGif().centerCrop().placeholder(R.drawable.iv_animal).load(R.drawable.egg_animation).into(binding.ivAnimal)
    }

    override fun setUpListener() {
        binding.apply {
            ivSetting.setOnClickAffect {
                showActivity(SettingsActivity::class.java, null)
            }
            llShop.setOnClickAffect {
                showActivity(AllShopActivity::class.java, null)
            }
            llPet.setOnClickAffect {
                val bundle = Bundle()
                bundle.putString(ADD_WIDGET, PET)
                showActivity(PetGardenActivity::class.java, bundle)
            }
            llPlant.setOnClickAffect {
                val bundle = Bundle()
                bundle.putString(ADD_WIDGET, PLANT)
                showActivity(PetGardenActivity::class.java, bundle)
            }
            cvPet.setOnClickAffect {
                val bundle = Bundle()
                bundle.putString(ADD_WIDGET, PET)
                showActivity(AddWidgetActivity::class.java, bundle)
            }
            cvPlant.setOnClickAffect {
                val bundle = Bundle()
                bundle.putString(ADD_WIDGET, PLANT)
                showActivity(AddWidgetActivity::class.java, bundle)
            }
        }
    }
}