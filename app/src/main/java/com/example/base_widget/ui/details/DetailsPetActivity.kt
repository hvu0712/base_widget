package com.example.base_widget.ui.details

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.os.postDelayed
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
import com.example.base_widget.model.PetModel
import com.example.base_widget.ui.shop.ItemCommon
import com.example.base_widget.utils.BaseConfig
import com.example.base_widget.utils.BaseConfig.DEFAULT_VALUE
import com.example.base_widget.utils.BaseConfig.DURATION
import com.example.base_widget.utils.BaseConfig.DURATION_PROGRESS_CHANGE
import com.example.base_widget.utils.BaseConfig.EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_1_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_2_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_3_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_4_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.PET_DETAILS
import com.example.base_widget.utils.BaseConfig.UPDATE
import com.example.base_widget.utils.BaseConfig.UPDATE_LIST
import com.example.base_widget.utils.BaseConfig.getGifByPos
import com.example.base_widget.utils.BaseConfig.hoursToMilliseconds
import com.example.base_widget.utils.BaseConfig.showExperienceUp

class DetailsPetActivity : BaseActivity<ActivityDetailsPetPlantBinding>() {

    private lateinit var itemPet: PetModel
    private var currentValue = 0
    private var appDb: AppDatabase = AppDatabase.getInstance(this)
    private val detailsAdapter = DetailsAdapter()
    override fun inflateViewBinding() = ActivityDetailsPetPlantBinding.inflate(layoutInflater)

    override fun initView() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setUpRecyclerView()
        val bundle = intent.extras
        itemPet = bundle?.getSerializable(PET_DETAILS) as PetModel
        binding.llSeekbarPet.show()
        binding.sbPlant.hide()
        binding.tvTime.hide()
        binding.ivPlant.hide()
        binding.ivPet.show()
        binding.ivPlants.hide()
        binding.ivPets.show()
        when (itemPet.type) {
            0 -> Glide.with(this).asGif().placeholder(R.drawable.normal_cat).load(itemPet.image).into(binding.ivPets)
            1 -> Glide.with(this).asGif().placeholder(R.drawable.normal_dog).load(itemPet.image).into(binding.ivPets)
            2 -> Glide.with(this).asGif().placeholder(R.drawable.normal_rabbit).load(itemPet.image).into(binding.ivPets)
        }
        binding.tvTitle.text = getString(R.string.tvPet)
        binding.tvName.text = itemPet.name
        binding.ivBackground.setImageResource(R.drawable.iv_animals)
        binding.tvName.text = itemPet.name
        binding.tvLevel.text = itemPet.level
        when (itemPet.level) {
            getString(R.string.tvLevel1) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = LEVEL_1_EXPERIENCE
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = LEVEL_1_EXPERIENCE
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }

            getString(R.string.tvLevel2) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = LEVEL_2_EXPERIENCE
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = LEVEL_2_EXPERIENCE
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }

            getString(R.string.tvLevel3) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = LEVEL_3_EXPERIENCE
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = LEVEL_3_EXPERIENCE
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }

            getString(R.string.tvLevel4) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }

            getString(R.string.tvLevelMax) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                    binding.sbPet.setValue(LEVEL_4_EXPERIENCE)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                            binding.sbPet.setValue(LEVEL_4_EXPERIENCE)
                        }
                    })
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvControl.apply {
            detailsAdapter.setData(BaseConfig.getItemPet(this@DetailsPetActivity))
            adapter = detailsAdapter
            layoutManager = GridLayoutManager(this@DetailsPetActivity, 2)
        }
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
        detailsAdapter.onItemClick = { item, position, iBinding ->
            handlerItemClick(iBinding,item,position,true)
        }
        binding.sbPet.setOnSeeBarChangeListener(object : ISetOnSeekBar {
            override fun onProgressChanged(seekbar: CustomSeeBar, value: Int) {
                currentValue = value
                when (appDb.petDao().getLevel(itemPet.id)) {
                    getString(R.string.tvLevel1) -> {
                        if (currentValue == LEVEL_1_EXPERIENCE) {
                            handlerProgressChange(getString(R.string.tvLevel1))
                        }
                    }

                    getString(R.string.tvLevel2) -> {
                        if (currentValue == LEVEL_2_EXPERIENCE) {
                            handlerProgressChange(getString(R.string.tvLevel2))
                        }
                    }

                    getString(R.string.tvLevel3) -> {
                        if (currentValue == LEVEL_3_EXPERIENCE) {
                            handlerProgressChange(getString(R.string.tvLevel3))
                        }
                    }

                    getString(R.string.tvLevel4) -> {
                        if (currentValue == LEVEL_4_EXPERIENCE) {
                            handlerProgressChange(getString(R.string.tvLevel4))
                        }
                    }

                }
            }
        })
    }

    private fun showGif(
        pos: Int,
        image: ImageView,
        isPet: Boolean,
        type: Int,
    ) {
        getGifByPos(this, pos, image, isPet, type)
        Handler(Looper.getMainLooper()).postDelayed({
            image.setImageResource(itemPet.image)
        }, DURATION)
    }
    private fun handlerItemClick(
        iBinding: ItemDetailsPetPlantBinding,
        item: ItemCommon,
        pos: Int,
        isPet: Boolean,
    ) {
        val level = appDb.petDao().getLevel(itemPet.id)
        if (level == getString(R.string.tvLevelMax)) {
            showExperienceUp(this,binding.tvExp,true)
        }
        else
        {
            showGif(pos, binding.ivPets, isPet, itemPet.type)
            currentValue += EXPERIENCE
            if (binding.sbPet.isCreated) {
                binding.sbPet.setValue(currentValue)
                Handler(Looper.getMainLooper()).postDelayed({
                    appDb.petDao().updatePetExperience(itemPet.id, currentValue)
                }, DURATION_PROGRESS_CHANGE)
            } else {
                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                    override fun isCreated() {
                        binding.sbPet.setValue(currentValue)
                        Handler(Looper.getMainLooper()).postDelayed({
                            appDb.petDao().updatePetExperience(itemPet.id, currentValue)
                        }, DURATION_PROGRESS_CHANGE)
                    }
                })
            }
            showExperienceUp(this, binding.tvExp, false)
            handlerCountDownTime(pos,item,iBinding)

        }
    }

    private fun handlerCountDownTime(pos: Int, item: ItemCommon, iBinding: ItemDetailsPetPlantBinding) {
        when(pos) {
            0 -> {
                SharePrefUtils.firstTimeStampPet1 = System.currentTimeMillis()
                SharePrefUtils.TimeStampPet1 = hoursToMilliseconds(item.time)
            }
            1 -> {
                SharePrefUtils.firstTimeStampPet2 = System.currentTimeMillis()
                SharePrefUtils.TimeStampPet2 = hoursToMilliseconds(item.time)
            }
            2 -> {
                SharePrefUtils.firstTimeStampPet3 = System.currentTimeMillis()
                SharePrefUtils.TimeStampPet3 = hoursToMilliseconds(item.time)
            }
            else -> {
                SharePrefUtils.firstTimeStampPet4 = System.currentTimeMillis()
                SharePrefUtils.TimeStampPet4 = hoursToMilliseconds(item.time)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            iBinding.root.enable()
            iBinding.tvTime.hide()
            iBinding.vCountDown.hide()
        }, hoursToMilliseconds(item.time))
    }
    override fun onResume() {
        super.onResume()
        Log.e("huy", "onResume: ")
        SharePrefUtils.currentTimeStampPet1 = System.currentTimeMillis()
        SharePrefUtils.currentTimeStampPet2 = System.currentTimeMillis()
        SharePrefUtils.currentTimeStampPet3 = System.currentTimeMillis()
        SharePrefUtils.currentTimeStampPet4 = System.currentTimeMillis()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("huy", "onDestroy: ")
        SharePrefUtils.latestTimeStampPet1 = System.currentTimeMillis()
        SharePrefUtils.latestTimeStampPet2 = System.currentTimeMillis()
        SharePrefUtils.latestTimeStampPet3 = System.currentTimeMillis()
        SharePrefUtils.latestTimeStampPet4 = System.currentTimeMillis()
    }

    private fun handlerProgressChange(level: String) {
        when(level) {
            getString(R.string.tvLevel1) -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (binding.sbPet.isCreated) {
                        binding.sbPet.setValue(DEFAULT_VALUE)
                        binding.sbPet.maxValue = LEVEL_2_EXPERIENCE
                        binding.tvLevel.text = getString(R.string.tvLevel2)
                        appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevel2))
                    } else {
                        binding.sbPet.setViewCreatedListener(object : CreatedListener {
                            override fun isCreated() {
                                binding.sbPet.setValue(DEFAULT_VALUE)
                                binding.sbPet.maxValue = LEVEL_2_EXPERIENCE
                                binding.tvLevel.text = getString(R.string.tvLevel2)
                                appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevel2))
                            }
                        })
                    }
                },DURATION_PROGRESS_CHANGE)
            }
            getString(R.string.tvLevel2) -> {
                Handler(Looper.getMainLooper()).postDelayed({
                        if (binding.sbPet.isCreated) {
                            binding.sbPet.setValue(DEFAULT_VALUE)
                            binding.sbPet.maxValue = LEVEL_3_EXPERIENCE
                            binding.tvLevel.text = getString(R.string.tvLevel3)
                            appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevel3))
                        } else {
                            binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                override fun isCreated() {
                                    binding.sbPet.setValue(DEFAULT_VALUE)
                                    binding.sbPet.maxValue = LEVEL_3_EXPERIENCE
                                    binding.tvLevel.text = getString(R.string.tvLevel3)
                                    appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevel3))
                                }
                            })
                        }
                },DURATION_PROGRESS_CHANGE)
            }
            getString(R.string.tvLevel3) -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (binding.sbPet.isCreated) {
                        binding.sbPet.setValue(DEFAULT_VALUE)
                        binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                        binding.tvLevel.text = getString(R.string.tvLevel4)
                        appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevel4))
                    } else {
                        binding.sbPet.setViewCreatedListener(object : CreatedListener {
                            override fun isCreated() {
                                binding.sbPet.setValue(DEFAULT_VALUE)
                                binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                                binding.tvLevel.text = getString(R.string.tvLevel4)
                                appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevel4))
                            }
                        })
                    }
                },DURATION_PROGRESS_CHANGE)
            }
            getString(R.string.tvLevel4) -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (binding.sbPet.isCreated) {
                        binding.tvLevel.text = getString(R.string.tvLevelMax)
                        binding.sbPet.setValue(LEVEL_4_EXPERIENCE)
                        binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                        appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevelMax))
                    } else {
                        binding.sbPet.setViewCreatedListener(object : CreatedListener {
                            override fun isCreated() {
                                binding.tvLevel.text = getString(R.string.tvLevelMax)
                                binding.sbPet.setValue(LEVEL_4_EXPERIENCE)
                                binding.sbPet.maxValue = LEVEL_4_EXPERIENCE
                                appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevelMax))
                            }
                        })
                    }
                }, DURATION_PROGRESS_CHANGE)
            }
        }
    }
}