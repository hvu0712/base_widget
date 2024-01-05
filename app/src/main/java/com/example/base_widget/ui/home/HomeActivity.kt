package com.example.base_widget.ui.home

import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.base_widget.R
import android.content.Intent
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityHomeBinding
import com.example.base_widget.ui.SettingsActivity
import com.example.base_widget.ui.shop.AllShopActivity
import com.example.base_widget.ui.shop.PlantShopActivity

class HomeActivity: BaseActivity<ActivityHomeBinding>() {

    override fun inflateViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun initView() {
        Glide.with(this@HomeActivity).asGif().centerCrop().placeholder(R.drawable.iv_plant).load(R.drawable.plant_animation).into(binding.ivPlant)
        Glide.with(this@HomeActivity).asGif().centerCrop().placeholder(R.drawable.iv_animal).load(R.drawable.egg_animation).into(binding.ivAnimal)
    }

    override fun setUpListener() {
        binding.apply {
            ivSetting.setOnClickAffect {

            }
            llShop.setOnClickAffect {
                startActivity(Intent(this@HomeActivity,AllShopActivity::class.java))
            }
            llAnimal.setOnClickAffect {

            }
            llPlant.setOnClickAffect {
                startActivity(Intent(this@HomeActivity,PlantShopActivity::class.java))
            }
            cvAnimal.setOnClickAffect {

            }
            cvPlant.setOnClickAffect {

            }
        }
    }
}