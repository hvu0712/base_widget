package com.example.base_widget.ui.details

import android.content.Intent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityDetailsPlantBinding
import com.example.base_widget.model.PlantModel
import com.example.base_widget.utils.AppUtils

class DetailsPlantActivity: BaseActivity<ActivityDetailsPlantBinding>() {

    private val detailsAdapter = DetailsAdapter()
    private lateinit var itemPlant: PlantModel
    private var currentValue = 0
    private var appDb: AppDatabase = AppDatabase.getInstance(this)
    override fun inflateViewBinding() = ActivityDetailsPlantBinding.inflate(layoutInflater)

    override fun initView() {
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
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

    private var onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val resultIntent = Intent()
            resultIntent.putExtra("update_list","update")
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            val resultIntent = Intent()
            resultIntent.putExtra("update_list","update")
            setResult(RESULT_OK, resultIntent)
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        binding.rvDetailsPlant.apply {
            detailsAdapter.setData(AppUtils.getItemPlant(this@DetailsPlantActivity))
            adapter = detailsAdapter
            layoutManager = GridLayoutManager(this@DetailsPlantActivity, 3)
        }
        detailsAdapter.onItemClick = {itemCommon, position ->
            when(position)
            {
                0 -> Toast.makeText(this,itemCommon.name, Toast.LENGTH_LONG).show()
                1 -> Toast.makeText(this,itemCommon.name, Toast.LENGTH_LONG).show()
                2 -> Toast.makeText(this,itemCommon.name, Toast.LENGTH_LONG).show()
                3 -> Toast.makeText(this,itemCommon.name, Toast.LENGTH_LONG).show()
            }
            currentValue += 10
            if (binding.sbPlant.isCreated) {
                binding.sbPlant.setValue(currentValue)

            } else {
                binding.sbPlant.setViewCreatedListener(object : CreatedListener {
                    override fun isCreated() {
                        binding.sbPlant.setValue(currentValue)

                    }
                })
            }
        }
    }
}