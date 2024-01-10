package com.example.base_widget.ui.home

import android.content.Intent
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityHomeBinding
import com.example.base_widget.ui.AddNewWidget
import com.example.base_widget.ui.GardenActivity
import com.example.base_widget.ui.PetsActivity
import com.example.base_widget.ui.details.DetailsPetActivity
import com.example.base_widget.ui.details.DetailsPlantActivity
import com.example.base_widget.ui.shop.AllShopActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun inflateViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun initView() {

    }

    override fun setUpListener() {
        binding.apply {
            ivSetting.setOnClickAffect {

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
                startActivity(Intent(this@HomeActivity, AddNewWidget::class.java))
            }
            cvPlant.setOnClickAffect {
                startActivity(Intent(this@HomeActivity, AddNewWidget::class.java))
            }
        }
    }
}