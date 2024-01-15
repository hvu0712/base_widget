package com.example.base_widget.ui.details

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.custom.CustomSeeBar
import com.example.base_widget.custom.ISetOnSeekBar
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityDetailsPetBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.ui.shop.ItemCommon
import kotlin.math.log

class DetailsPetActivity : BaseActivity<ActivityDetailsPetBinding>(), DetailsFragmentListener {

    private lateinit var itemPet: PetModel
    private var currentValue = 0
    private var maxValue = 0
    private var appDb: AppDatabase = AppDatabase.getInstance(this)
    override fun inflateViewBinding() = ActivityDetailsPetBinding.inflate(layoutInflater)

    override fun initView() {
        binding.viewPager.adapter = MyViewPagerAdapter()
        binding.viewPager.registerOnPageChangeCallback(viewPagerListener)
        val bundle = intent.extras
        itemPet = bundle?.getSerializable("pet_details") as PetModel
        binding.tvPet.text = itemPet.name
        binding.tvLevel.text = itemPet.level
        binding.ivPet.setImageResource(itemPet.image)
        when(itemPet.level)
        {
            getString(R.string.tvLevel1) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = 100
                    maxValue = 100
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = 100
                            maxValue = 100
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }
            getString(R.string.tvLevel2) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = 150
                    maxValue = 150
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = 150
                            maxValue = 150
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }
            getString(R.string.tvLevel3) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = 200
                    maxValue = 200
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = 200
                            maxValue = 200
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }
            getString(R.string.tvLevel4) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.maxValue = 250
                    maxValue = 250
                    binding.sbPet.setValue(itemPet.experience)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.maxValue = 250
                            maxValue = 250
                            binding.sbPet.setValue(itemPet.experience)
                        }
                    })
                }
            }
            getString(R.string.tvLevelMax) -> {
                if (binding.sbPet.isCreated) {
                    binding.sbPet.setValue(500)
                    binding.tvLevel.text = getString(R.string.tvLevelMax)
                } else {
                    binding.sbPet.setViewCreatedListener(object : CreatedListener {
                        override fun isCreated() {
                            binding.sbPet.setValue(500)
                            binding.tvLevel.text = getString(R.string.tvLevelMax)
                        }
                    })
                }
            }
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.llFoodSelected.setOnClickAffect {
            binding.viewPager.currentItem = 0
        }
        binding.llFoodUnSelected.setOnClickAffect {
            binding.viewPager.currentItem = 0
        }
        binding.llToiletSelected.setOnClickAffect {
            binding.viewPager.currentItem = 1
        }
        binding.llToiletUnSelected.setOnClickAffect {
            binding.viewPager.currentItem = 1
        }
        binding.llSleepSelected.setOnClickAffect {
            binding.viewPager.currentItem = 2
        }
        binding.llSleepUnSelected.setOnClickAffect {
            binding.viewPager.currentItem = 2
        }
        binding.sbPet.setOnSeeBarChangeListener(object : ISetOnSeekBar{
            override fun onProgressChanged(seekbar: CustomSeeBar, value: Int) {
                currentValue = value
                Log.e("huy", "onProgressChanged: ${currentValue}", )
                when (itemPet.level)
                {
                    getString(R.string.tvLevel1) -> {
                        if (currentValue == 100)
                        {
                            if (binding.sbPet.isCreated) {
                                binding.sbPet.setValue(0)
                                binding.sbPet.maxValue = 150
                                maxValue = 150
                                binding.tvLevel.text = getString(R.string.tvLevel2)
//                                appDb.petDao().updatePetLevel()

                            } else {
                                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                    override fun isCreated() {
                                        binding.sbPet.setValue(0)
                                        binding.sbPet.maxValue = 150
                                        maxValue = 150
                                        binding.tvLevel.text = getString(R.string.tvLevel2)
//                                        appDb.petDao().updatePetLevel()
                                    }
                                })
                            }
                        }
                    }
                    getString(R.string.tvLevel2) -> {
                        if (currentValue == 150)
                        {
                            if (binding.sbPet.isCreated) {
                                binding.sbPet.setValue(0)
                                binding.sbPet.maxValue = 200
                                maxValue = 200
                                binding.tvLevel.text = getString(R.string.tvLevel3)
//                                appDb.petDao().updatePetLevel()
                            } else {
                                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                    override fun isCreated() {
                                        binding.sbPet.setValue(0)
                                        binding.sbPet.maxValue = 200
                                        maxValue = 200
                                        binding.tvLevel.text = getString(R.string.tvLevel3)
//                                        appDb.petDao().updatePetLevel()
                                    }
                                })
                            }
                        }
                    }
                    getString(R.string.tvLevel3) -> {
                        if (currentValue == 200)
                        {
                            if (binding.sbPet.isCreated) {
                                binding.sbPet.setValue(0)
                                binding.sbPet.maxValue = 250
                                maxValue = 250
                                binding.tvLevel.text = getString(R.string.tvLevel4)
//                                appDb.petDao().updatePetLevel()
                            } else {
                                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                    override fun isCreated() {
                                        binding.sbPet.setValue(0)
                                        binding.sbPet.maxValue = 250
                                        maxValue = 250
                                        binding.tvLevel.text = getString(R.string.tvLevel4)
//                                        appDb.petDao().updatePetLevel()
                                    }
                                })
                            }
                        }
                    }
                    getString(R.string.tvLevel4) -> {
                        if (currentValue == 250)
                        {
                            if (binding.sbPet.isCreated) {
                                binding.sbPet.setValue(500)
                                binding.tvLevel.text = getString(R.string.tvLevelMax)
//                                appDb.petDao().updatePetLevel()
                            } else {
                                binding.sbPet.setViewCreatedListener(object : CreatedListener {
                                    override fun isCreated() {
                                        binding.sbPet.setValue(500)
                                        binding.tvLevel.text = getString(R.string.tvLevelMax)
//                                        appDb.petDao().updatePetLevel()
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

    private var viewPagerListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> {
                    binding.llFoodSelected.show()
                    binding.llFoodUnSelected.hide()
                    binding.llToiletSelected.hide()
                    binding.llToiletUnSelected.show()
                    binding.llSleepSelected.hide()
                    binding.llSleepUnSelected.show()
                }

                1 -> {
                    binding.llFoodSelected.hide()
                    binding.llFoodUnSelected.show()
                    binding.llToiletSelected.show()
                    binding.llToiletUnSelected.hide()
                    binding.llSleepSelected.hide()
                    binding.llSleepUnSelected.show()
                }

                2 -> {
                    binding.llFoodSelected.hide()
                    binding.llFoodUnSelected.show()
                    binding.llToiletSelected.hide()
                    binding.llToiletUnSelected.show()
                    binding.llSleepSelected.show()
                    binding.llSleepUnSelected.hide()
                }
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class MyViewPagerAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("page", position)
            return DetailsFragment().apply {
                arguments = bundle
                setListener(this@DetailsPetActivity)
            }
        }
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerListener)
        super.onDestroy()
    }

    override fun onDetailsItemSelected(itemCommon: ItemCommon, position: Int) {
        when(position)
        {
            0 -> Toast.makeText(this,itemCommon.name,Toast.LENGTH_LONG).show()
            1 -> Toast.makeText(this,itemCommon.name,Toast.LENGTH_LONG).show()
            2 -> Toast.makeText(this,itemCommon.name,Toast.LENGTH_LONG).show()
        }
        currentValue += 10
        if (binding.sbPet.isCreated) {
            binding.sbPet.setValue(currentValue)
//            appDb.petDao().updatePetExperience()
        } else {
            binding.sbPet.setViewCreatedListener(object : CreatedListener {
                override fun isCreated() {
                    binding.sbPet.setValue(currentValue)
//                    appDb.petDao().updatePetExperience()
                }
            })
        }
    }
}