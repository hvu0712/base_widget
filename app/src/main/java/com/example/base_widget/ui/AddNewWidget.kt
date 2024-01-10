package com.example.base_widget.ui

import android.content.Intent
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityAddWidgetBinding
import com.example.base_widget.ui.details.PlantSelectActivity

class AddNewWidget: BaseActivity<ActivityAddWidgetBinding>() {
    override fun inflateViewBinding() = ActivityAddWidgetBinding.inflate(layoutInflater)
    override fun initView() {

    }

    override fun setUpListener() {
        binding.llSelect.setOnClickAffect {
            startActivity(Intent(this, PlantSelectActivity::class.java))
        }
    }
}