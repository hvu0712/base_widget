package com.example.base_widget.ui.details

import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityPetSelectBinding
import com.example.base_widget.databinding.ActivityPlantSelectBinding
import com.example.base_widget.ui.shop.GridSpacingItemDecoration
import com.example.base_widget.utils.AppUtils

class PetSelectActivity: BaseActivity<ActivityPetSelectBinding>() {

    private val allSelectAdapter = AllSelectAdapter()
    override fun inflateViewBinding() = ActivityPetSelectBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._38sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        binding.rvAllPet.apply {
            allSelectAdapter.setData(AppUtils.getItemSelect())
            adapter = allSelectAdapter
            layoutManager = GridLayoutManager(this@PetSelectActivity, 4)
            addItemDecoration(GridSpacingItemDecoration(4,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            finish()
        }
    }
}