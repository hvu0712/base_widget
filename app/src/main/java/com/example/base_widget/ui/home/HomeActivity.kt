package com.example.base_widget.ui.home

import android.content.Intent
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityHomeBinding
import com.example.base_widget.ui.shop.AllShopActivity

class HomeActivity: BaseActivity<ActivityHomeBinding>() {

    override fun inflateViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun initView() {

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

            }
            cvAnimal.setOnClickAffect {

            }
            cvPlant.setOnClickAffect {

            }
        }
    }
}