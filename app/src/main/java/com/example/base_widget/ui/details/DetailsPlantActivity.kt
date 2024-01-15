package com.example.base_widget.ui.details

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.databinding.ActivityDetailsPlantBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
import com.example.base_widget.utils.AppUtils

class DetailsPlantActivity: BaseActivity<ActivityDetailsPlantBinding>() {

    private val detailsAdapter = DetailsAdapter()
    private lateinit var itemPlant: PlantModel
    override fun inflateViewBinding() = ActivityDetailsPlantBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
        val bundle = intent.extras
        itemPlant = bundle?.getSerializable("plant_details") as PlantModel
        binding.tvPlant.text = itemPlant.name
        binding.ivTree.setImageResource(itemPlant.image)
//        if (binding.sbPlant.isCreated) {
//            binding.sbPlant.setValue(itemPlant.maturityTime.toInt())
//        } else {
//            binding.sbPlant.setViewCreatedListener(object : CreatedListener {
//                override fun isCreated() {
//                    binding.sbPlant.setValue(itemPlant.maturityTime.toInt())
//                }
//
//            })
//        }

    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            onBackPressedDispatcher.onBackPressed()
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