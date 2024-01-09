package com.example.base_widget.ui.shop

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.databinding.ActivityDetailsPetBinding

class DetailsPetActivity : BaseActivity<ActivityDetailsPetBinding>() {

    private val detailsAdapter = DetailsAdapter()
    override fun inflateViewBinding() = ActivityDetailsPetBinding.inflate(layoutInflater)

    override fun initView() {
        binding.viewPager.adapter = MyViewPagerAdapter()
        binding.viewPager.registerOnPageChangeCallback(viewPagerListener)
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            finish()
        }
        detailsAdapter.onItemClick = {
            Toast.makeText(this, "ok", Toast.LENGTH_LONG).show()
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
        if (binding.sbPet.isCreated) {
            binding.sbPet.setValue(0)
        } else {
            binding.sbPet.setViewCreatedListener(object : CreatedListener {
                override fun isCreated() {
                    binding.sbPet.setValue(0)
                }

            })
        }
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
            return DetailsFragment().apply { arguments = bundle }
        }
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerListener)
        super.onDestroy()
    }
}