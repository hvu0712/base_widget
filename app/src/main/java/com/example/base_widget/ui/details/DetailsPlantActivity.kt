package com.example.base_widget.ui.details

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityDetailsPetPlantBinding
import com.example.base_widget.model.PlantModel
import com.example.base_widget.utils.BaseConfig
import com.example.base_widget.utils.BaseConfig.DURATION
import com.example.base_widget.utils.BaseConfig.EXPERIENCE
import com.example.base_widget.utils.BaseConfig.PLANT_DETAILS
import com.example.base_widget.utils.BaseConfig.UPDATE
import com.example.base_widget.utils.BaseConfig.UPDATE_LIST
import com.example.base_widget.utils.BaseConfig.getGifByPos

class DetailsPlantActivity : BaseActivity<ActivityDetailsPetPlantBinding>() {

    private val detailsAdapter = DetailsAdapter()
    private lateinit var itemPlant: PlantModel
    private var currentValue = 0
    private var appDb: AppDatabase = AppDatabase.getInstance(this)
    override fun inflateViewBinding() = ActivityDetailsPetPlantBinding.inflate(layoutInflater)

    override fun initView() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setUpRecyclerView()
        val bundle = intent.extras
        itemPlant = bundle?.getSerializable(PLANT_DETAILS) as PlantModel
        binding.llSeekbarPet.hide()
        binding.sbPlant.show()
        binding.tvTime.show()
        binding.tvTime.text = getString(R.string.tvSproutIn)
        binding.ivPet.hide()
        binding.tvTitle.text = getString(R.string.tvPlant)
        binding.ivPlant.show()
        binding.tvName.text = itemPlant.name
        binding.ivPlants.show()
        binding.ivPets.hide()
        binding.ivPlants.setImageResource(itemPlant.image)
        binding.ivBackground.setImageResource(R.drawable.iv_plant)
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
            resultIntent.putExtra(UPDATE_LIST, UPDATE)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            val resultIntent = Intent()
            resultIntent.putExtra(UPDATE_LIST, UPDATE)
            setResult(RESULT_OK, resultIntent)
            onBackPressedDispatcher.onBackPressed()
        }
        detailsAdapter.onItemClick = { _, position ->
            when (position) {
                0 -> showGif(this, position, binding.ivPlants, false)
                1 -> showGif(this, position, binding.ivPlants, false)
                2 -> showGif(this, position, binding.ivPlants, false)
                else -> showGif(this, position, binding.ivPlants, false)
            }
            currentValue += EXPERIENCE
//            if (binding.sbPet.isCreated) {
//                binding.sbPet.setValue(currentValue)
//
//            } else {
//                binding.sbPet.setViewCreatedListener(object : CreatedListener {
//                    override fun isCreated() {
//                        binding.sbPet.setValue(currentValue)
//
//                    }
//                })
//            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvControl.apply {
            detailsAdapter.setData(BaseConfig.getItemPlant(this@DetailsPlantActivity))
            adapter = detailsAdapter
            layoutManager = GridLayoutManager(this@DetailsPlantActivity, 2)
        }
    }

    private fun showGif(
        activity: DetailsPlantActivity,
        pos: Int,
        image: ImageView,
        isPet: Boolean
    ) {
        getGifByPos(activity, pos, image, isPet)
        Handler(Looper.getMainLooper()).postDelayed({
            image.setImageResource(itemPlant.image)
        }, DURATION)
    }
}