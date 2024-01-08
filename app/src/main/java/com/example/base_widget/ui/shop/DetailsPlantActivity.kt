package com.example.base_widget.ui.shop

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.databinding.ActivityDetailsPlantBinding
import com.example.base_widget.utils.AppUtils

class DetailsPlantActivity: BaseActivity<ActivityDetailsPlantBinding>() {

    private val detailsAdapter = DetailsAdapter()
    override fun inflateViewBinding() = ActivityDetailsPlantBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            finish()
        }
        detailsAdapter.onItemClick = {
            Toast.makeText(this,"ok", Toast.LENGTH_LONG).show()
        }
        if (binding.sbPlant.isCreated) {
            binding.sbPlant.setValue(100)
        } else {
            binding.sbPlant.setViewCreatedListener(object : CreatedListener {
                override fun isCreated() {
                    binding.sbPlant.setValue(100)
                }

            })
        }
    }

    private fun setUpRecyclerView() {
        binding.rvDetailsPlant.apply {
            detailsAdapter.setData(AppUtils.getItemDetailsPlant())
            adapter = detailsAdapter
            layoutManager = GridLayoutManager(this@DetailsPlantActivity, 3)
        }
    }
}