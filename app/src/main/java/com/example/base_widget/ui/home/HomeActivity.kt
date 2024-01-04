package com.example.base_widget.ui.home

import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityHomeBinding
import com.example.base_widget.ui.SettingsActivity

class HomeActivity: BaseActivity<ActivityHomeBinding>() {

    override fun inflateViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun initView() {
        Glide.with(this@HomeActivity).asGif().placeholder(R.drawable.iv_plant).load(R.raw.plant_animation).into(binding.ivPlant)
    }

    override fun setUpListener() {
        binding.apply {
            ivSetting.setOnClickAffect {
                showActivity(SettingsActivity::class.java, null)
            }
            llShop.setOnClickAffect {

            }
            llAnimal.setOnClickAffect {

            }
            llPlant.setOnClickAffect {

            }
            cvAnimal.setOnClickAffect {

            }
//            cvPlant.setOnClickAffect {
//
//            }
        }
    }
}