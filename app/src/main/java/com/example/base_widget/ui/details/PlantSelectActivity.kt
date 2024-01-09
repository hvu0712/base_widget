package com.example.base_widget.ui.details

import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityPlantSelectBinding
import com.example.base_widget.utils.AppUtils

class PlantSelectActivity: BaseActivity<ActivityPlantSelectBinding>() {

    private val allSelectAdapter = AllSelectAdapter()
    override fun inflateViewBinding() = ActivityPlantSelectBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvAllPlant.apply {
            allSelectAdapter.setData(AppUtils.getItemSelect())
            adapter = allSelectAdapter
            layoutManager = GridLayoutManager(this@PlantSelectActivity, 3)
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            finish()
        }
    }
}