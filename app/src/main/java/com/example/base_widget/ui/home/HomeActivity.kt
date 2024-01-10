package com.example.base_widget.ui.home

import com.bumptech.glide.Glide
import com.example.base_widget.R
import android.content.Intent
import android.os.Bundle
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityHomeBinding
import com.example.base_widget.ui.AddWidgetActivity
import com.example.base_widget.ui.SettingsActivity
import com.example.base_widget.ui.GardenActivity
import com.example.base_widget.ui.PetsActivity
import com.example.base_widget.ui.shop.AllShopActivity

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
                startActivity(Intent(this@HomeActivity, AllShopActivity::class.java))
            }
            llPet.setOnClickAffect {
                startActivity(Intent(this@HomeActivity, PetsActivity::class.java))
            }
            llPlant.setOnClickAffect {
                startActivity(Intent(this@HomeActivity, GardenActivity::class.java))
            }
            cvPet.setOnClickAffect {
                val bundle = Bundle()
                bundle.putString("addWidget", "pet")
                showActivity(AddWidgetActivity::class.java, null)
            }
            cvPlant.setOnClickAffect {
                val bundle = Bundle()
                bundle.putString("addWidget", "plant")
                showActivity(AddWidgetActivity::class.java, bundle)
            }
        }
    }
}