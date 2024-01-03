package com.example.base_widget.ui.shop

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ActivityShopBinding
import com.example.base_widget.utils.AppUtils

class ShopActivity : BaseActivity<ActivityShopBinding>() {

    private val petShopAdapter = PetShopAdapter()
    override fun inflateViewBinding() = ActivityShopBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerView()
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            onBackPressedDispatcher.onBackPressed()
        }
        petShopAdapter.onItemClick = {
            Toast.makeText(this, "ok", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._22sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._48sdp)
        binding.rvPetShop.apply {
            petShopAdapter.setData(AppUtils.getItemPetShop())
            adapter = petShopAdapter
            layoutManager = GridLayoutManager(this@ShopActivity, 3)
            addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo))

        }

    }
}