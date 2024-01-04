package com.example.base_widget.ui.shop

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityAllShopBinding
import com.example.base_widget.databinding.ActivityPlantShopBinding
import com.example.base_widget.utils.AppUtils

class PlantShopActivity : BaseActivity<ActivityPlantShopBinding>() {

    private val shopAdapter = ShopAdapter()
    override fun inflateViewBinding() = ActivityPlantShopBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
//            onBackPressedDispatcher.onBackPressed()
            finish()
        }
        shopAdapter.onItemClick = {
            Toast.makeText(this, "ok", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._32sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._58sdp)
        val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._56sdp)
        binding.rvPlantShop.apply {
            shopAdapter.setData(AppUtils.getItemPlantShop())
            adapter = shopAdapter
            layoutManager = GridLayoutManager(this@PlantShopActivity, 3)
            addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))

        }

    }
}