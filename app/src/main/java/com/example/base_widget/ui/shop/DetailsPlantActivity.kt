package com.example.base_widget.ui.shop

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityDetailsPlantBinding
import com.example.base_widget.utils.AppUtils

class DetailsPlantActivity: BaseActivity<ActivityDetailsPlantBinding>() {

    private val detailsPlantAdapter = DetailsPlantAdapter()
    override fun inflateViewBinding() = ActivityDetailsPlantBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            onBackPressedDispatcher.onBackPressed()
        }
        detailsPlantAdapter.onItemClick = {
            Toast.makeText(this,"ok", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpRecyclerView() {
        binding.rvDetailsPlant.apply {
            detailsPlantAdapter.setData(AppUtils.getItemDetailsPlant())
            adapter = detailsPlantAdapter
            layoutManager = GridLayoutManager(this@DetailsPlantActivity, 3)
        }
    }
}