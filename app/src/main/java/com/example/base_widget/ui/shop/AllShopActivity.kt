package com.example.base_widget.ui.shop

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.databinding.ActivityAllShopBinding

class AllShopActivity : BaseActivity<ActivityAllShopBinding>() {

    private val shopAdapter = ShopAdapter()
    override fun inflateViewBinding() = ActivityAllShopBinding.inflate(layoutInflater)

    override fun initView() {
        binding.viewPager.adapter = MyViewPagerAdapter()
        binding.viewPager.registerOnPageChangeCallback(viewPagerListener)
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            onBackPressedDispatcher.onBackPressed()
        }
        shopAdapter.onItemClick = {itemTraining, position ->
            Toast.makeText(this, "ok", Toast.LENGTH_LONG).show()
        }
        binding.llPlant.setOnClickListener {
            binding.viewPager.currentItem = 1
        }
        binding.llPlantDisable.setOnClickListener {
            binding.viewPager.currentItem = 1
        }
        binding.llPet.setOnClickListener {
            binding.viewPager.currentItem = 0
        }
        binding.llPetDisable.setOnClickListener {
            binding.viewPager.currentItem = 0
        }
    }

    private var viewPagerListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> {
                    binding.llPet.show()
                    binding.llPetDisable.hide()
                    binding.llPlantDisable.show()
                    binding.llPlant.hide()
                }

                1 -> {
                    binding.llPet.hide()
                    binding.llPetDisable.show()
                    binding.llPlantDisable.hide()
                    binding.llPlant.show()
                }
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class MyViewPagerAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("page", position)
            return ShopFragment().apply { arguments = bundle }
        }
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerListener)
        super.onDestroy()
    }
}