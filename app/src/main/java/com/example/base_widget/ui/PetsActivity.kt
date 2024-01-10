package com.example.base_widget.ui

import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityPetsBinding
import com.example.base_widget.ui.details.AllSelectAdapter
import com.example.base_widget.ui.shop.GridSpacingItemDecoration
import com.example.base_widget.ui.shop.ShopAdapter
import com.example.base_widget.utils.AppUtils

class PetsActivity: BaseActivity<ActivityPetsBinding>() {

    private val allSelectAdapter = AllSelectAdapter()
    private var shopAdapter = ShopAdapter()
    override fun inflateViewBinding() = ActivityPetsBinding.inflate(layoutInflater)
    override fun initView() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._1sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._54sdp)
        val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
        binding.rvAllSeed.apply {
            shopAdapter.setData(AppUtils.getItemPlantShop())
            adapter = shopAdapter
            layoutManager = GridLayoutManager(this@PetsActivity, 3)
            addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
        }
        val spacingInPixelsOneP = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._1sdp)
        val spacingInPixelsTwoP = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        val spacingInPixelsThreeP = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        binding.rvAllPet.apply {
            allSelectAdapter.setData(AppUtils.getItemSelect())
            adapter = allSelectAdapter
            layoutManager = GridLayoutManager(this@PetsActivity, 4)
            addItemDecoration(GridSpacingItemDecoration(4,spacingInPixelsOneP, spacingInPixelsTwoP, spacingInPixelsThreeP))
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            finish()
        }
    }
}