package com.example.base_widget.ui

import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.databinding.ActivityAddWidgetBinding

class AddWidgetActivity : BaseActivity<ActivityAddWidgetBinding>(){
    override fun inflateViewBinding() = ActivityAddWidgetBinding.inflate(layoutInflater)

    override fun initView() {
        val bundle = intent.extras
        binding.apply {
            ivBack.setOnSingleClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        if (bundle?.getString("addWidget").equals("pet")) {
            binding.ivWidget.setImageDrawable(getDrawable(R.drawable.iv_widget_s))
        } else if (bundle?.getString("addWidget").equals("plant")) {
            binding.ivWidget.setImageDrawable(getDrawable(R.drawable.iv_preview_plant))
        }
    }

    override fun setUpListener() {
    }
}