package com.example.base_widget.ui.details

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.SharePrefUtils
import com.example.base_widget.common.enable
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.custom.CustomSeeBar
import com.example.base_widget.custom.ISetOnSeekBar
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityDetailsPetPlantBinding
import com.example.base_widget.databinding.ItemDetailsPetPlantBinding
import com.example.base_widget.model.PlantModel
import com.example.base_widget.utils.BaseConfig
import com.example.base_widget.utils.BaseConfig.DURATION
import com.example.base_widget.utils.BaseConfig.EXPERIENCE
import com.example.base_widget.utils.BaseConfig.PLANT_DETAILS
import com.example.base_widget.utils.BaseConfig.UPDATE
import com.example.base_widget.utils.BaseConfig.UPDATE_LIST
import com.example.base_widget.utils.BaseConfig.getGifByPos
import com.example.base_widget.utils.BaseConfig.showExperienceUp

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
        when (itemPlant.type) {
            0 -> Glide.with(this).asGif().placeholder(R.drawable.iv_rose).load(itemPlant.image).into(binding.ivPlants)
            1 -> Glide.with(this).asGif().placeholder(R.drawable.iv_sunflower).load(itemPlant.image).into(binding.ivPlants)
            2 -> Glide.with(this).asGif().placeholder(R.drawable.iv_peach_blossom).load(itemPlant.image).into(binding.ivPlants)
        }
        binding.ivBackground.setImageResource(R.drawable.iv_plant)
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
        detailsAdapter.onItemClick = { _, position, iBinding ->
            handlerItemClick(iBinding,position,false)
        }
        binding.sbPlant.setOnSeeBarChangeListener(object : ISetOnSeekBar {
            override fun onProgressChanged(seekbar: CustomSeeBar, value: Int) {

            }
        })
    }

    private fun setUpRecyclerView() {
        binding.rvControl.apply {
            detailsAdapter.setData(BaseConfig.getItemPlant(this@DetailsPlantActivity))
            adapter = detailsAdapter
            layoutManager = GridLayoutManager(this@DetailsPlantActivity, 2)
        }
    }

    private fun showGif(
        pos: Int,
        image: ImageView,
        isPet: Boolean,
        type: Int,
    ) {
        getGifByPos(this, pos, image, isPet, type)
        Handler(Looper.getMainLooper()).postDelayed({
            image.setImageResource(itemPlant.image)
        }, DURATION)
    }
    private fun handlerItemClick(
        iBinding: ItemDetailsPetPlantBinding,
        pos: Int,
        isPet: Boolean,
    ) {
        val level = appDb.petDao().getLevel(itemPlant.id)
        if (level == getString(R.string.tvLevelMax)) {
            showExperienceUp(this, binding.tvExp, true)
        }
        else
        {
            showGif(pos, binding.ivPlants, isPet, itemPlant.type)
            currentValue += EXPERIENCE
            if (binding.sbPlant.isCreated) {

            } else {
                binding.sbPlant.setViewCreatedListener(object : CreatedListener {
                    override fun isCreated() {

                    }
                })
            }
            showExperienceUp(this, binding.tvExp, false)
            handlerCountDownTime(pos,iBinding)

        }
    }

    private fun handlerCountDownTime(pos: Int, iBinding: ItemDetailsPetPlantBinding) {
        when(pos) {
            0 -> SharePrefUtils.firstTimeStampPlant1 = System.currentTimeMillis()
            1 -> SharePrefUtils.firstTimeStampPlant2 = System.currentTimeMillis()
            2 -> SharePrefUtils.firstTimeStampPlant3 = System.currentTimeMillis()
            else -> SharePrefUtils.firstTimeStampPlant4 = System.currentTimeMillis()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            iBinding.root.enable()
            iBinding.tvTime.hide()
            iBinding.vCountDown.hide()
        }, DURATION)
    }
}