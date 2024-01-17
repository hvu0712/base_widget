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
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.custom.CustomSeeBar
import com.example.base_widget.custom.ISetOnSeekBar
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityDetailsPetPlantBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.utils.BaseConfig
import com.example.base_widget.utils.BaseConfig.DEFAULT_VALUE
import com.example.base_widget.utils.BaseConfig.EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_1_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_2_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_3_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_4_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.LEVEL_MAX_EXPERIENCE
import com.example.base_widget.utils.BaseConfig.PET_DETAILS
import com.example.base_widget.utils.BaseConfig.UPDATE
import com.example.base_widget.utils.BaseConfig.UPDATE_LIST
import com.example.base_widget.utils.BaseConfig.getGifByPos
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
        binding.ivPets.setImageResource(itemPet.image)
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
                    binding.sbPet.setValue(LEVEL_MAX_EXPERIENCE)
                    binding.tvLevel.text = getString(R.string.tvLevelMax)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.setValue(LEVEL_MAX_EXPERIENCE)
                            binding.tvLevel.text = getString(R.string.tvLevelMax)
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
        detailsAdapter.onItemClick = { _, position ->
            when (position) {
                0 -> showGif(this, position, binding.ivPets, true)
                1 -> showGif(this, position, binding.ivPets, true)
                2 -> showGif(this, position, binding.ivPets, true)
                else -> showGif(this, position, binding.ivPets, true)
            }
            currentValue += EXPERIENCE
            if (binding.sbPet.isCreated) {
                val level = appDb.petDao().getLevel(itemPet.id)
                binding.sbPet.setValue(currentValue)
                appDb.petDao().updatePetExperience(itemPet.id, currentValue)
            } else {
                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                    override fun isCreated() {
                        binding.sbPet.setValue(currentValue)
                        appDb.petDao().updatePetExperience(itemPet.id, currentValue)
                    }
                })
            }
            showExperienceUp(this,binding.tvExp)
        }
        binding.sbPet.setOnSeeBarChangeListener(object : ISetOnSeekBar {
            override fun onProgressChanged(seekbar: CustomSeeBar, value: Int) {
                currentValue = value
                when (itemPet.level) {
                    getString(R.string.tvLevel1) -> {
                        if (currentValue == LEVEL_1_EXPERIENCE) {
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
                        }
                    }

                    getString(R.string.tvLevel2) -> {
                        if (currentValue == LEVEL_2_EXPERIENCE) {
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
                        }
                    }

                    getString(R.string.tvLevel3) -> {
                        if (currentValue == LEVEL_3_EXPERIENCE) {
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
                        }
                    }

                    getString(R.string.tvLevel4) -> {
                        if (currentValue == LEVEL_4_EXPERIENCE) {
                            if (binding.sbPet.isCreated) {
                                binding.sbPet.setValue(LEVEL_MAX_EXPERIENCE)
                                binding.tvLevel.text = getString(R.string.tvLevelMax)
                                appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevelMax))
                            } else {
                                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                    override fun isCreated() {
                                        binding.sbPet.setValue(LEVEL_MAX_EXPERIENCE)
                                        binding.tvLevel.text = getString(R.string.tvLevelMax)
                                        appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevelMax))
                                    }
                                })
                            }
                        }
                    }

                    getString(R.string.tvLevelMax) -> {
                        if (currentValue >= LEVEL_MAX_EXPERIENCE) {
                            if (binding.sbPet.isCreated) {
                                binding.sbPet.setValue(LEVEL_MAX_EXPERIENCE)
                                binding.tvLevel.text = getString(R.string.tvLevelMax)
                                appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevelMax))
                            } else {
                                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                    override fun isCreated() {
                                        binding.sbPet.setValue(LEVEL_MAX_EXPERIENCE)
                                        binding.tvLevel.text = getString(R.string.tvLevelMax)
                                        appDb.petDao().updatePetLevel(itemPet.id, getString(R.string.tvLevelMax))
                                    }
                                })
                            }
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(seekbar: CustomSeeBar) {

            }

            override fun onStopTrackingTouch(seekbar: CustomSeeBar) {

            }

        })
    }

    private fun showGif(
        activity: DetailsPetActivity,
        pos: Int,
        image: ImageView,
        isPet: Boolean
    ) {
        getGifByPos(activity, pos, image, isPet)
        Handler(Looper.getMainLooper()).postDelayed({
            image.setImageResource(itemPet.image)
        },500)
    }
}