package com.example.base_widget.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityPetGardenBinding
import com.example.base_widget.ui.details.DetailsPetActivity
import com.example.base_widget.ui.details.DetailsPlantActivity
import com.example.base_widget.ui.details.PetSelectAdapter
import com.example.base_widget.ui.details.PlantSelectAdapter
import com.example.base_widget.ui.shop.GridSpacingItemDecoration
import com.example.base_widget.ui.shop.ShopAdapter
import com.example.base_widget.utils.AppUtils

class PetGardenActivity : BaseActivity<ActivityPetGardenBinding>() {

    private val plantSelectAdapter = PlantSelectAdapter()
    private val petSelectAdapter = PetSelectAdapter()
    private var shopAdapter = ShopAdapter()
    private var valueBundle: String? = null
    private var detailsPopup: PopupWindow? = null
    override fun inflateViewBinding() = ActivityPetGardenBinding.inflate(layoutInflater)
    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString("addWidget")
        if (valueBundle.equals("pet")) {
            binding.tvAllPlantPet.text = getString(R.string.tvAllPet)
            binding.tvSeedEggs.text = getString(R.string.tvEggs)
            binding.tvPlantPet.text = getString(R.string.tvPet)
            shopAdapter.setData(AppUtils.getItemPetShop())
            petSelectAdapter.setData(AppDatabase.getInstance(this).petDao().getAllPet())
        } else if (valueBundle.equals("plant")) {
            binding.tvAllPlantPet.text = getString(R.string.tvAllPlant)
            binding.tvSeedEggs.text = getString(R.string.tvSeed)
            binding.tvPlantPet.text = getString(R.string.tvPlant)
            shopAdapter.setData(AppUtils.getItemPlantShop())
            plantSelectAdapter.setData(AppDatabase.getInstance(this).plantDao().getAllPlant())
        }
        setUpRecyclerView()
        setUpPopupDetails()
    }

    private fun showPopupDetails() {
        val anchorView = IntArray(2)
        binding.ctlQuality.getLocationInWindow(anchorView)
        detailsPopup?.showAtLocation(
            window.decorView, Gravity.NO_GRAVITY,
            anchorView[0] + binding.ctlQuality.width / 2,
            anchorView[1] + binding.ctlQuality.height - 20
        )
    }

    private fun setUpPopupDetails() {
        val popupView = LayoutInflater.from(this).inflate(R.layout.popup_quality, null)
        detailsPopup = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        detailsPopup?.elevation = 10f
        popupView.findViewById<View>(R.id.txtLow).setOnClickListener {
            quality = Quality.LOW
            popupView.findViewById<View>(R.id.imgLow).alpha = 1f
            popupView.findViewById<View>(R.id.imgHigh).alpha = 0f
            popupView.findViewById<View>(R.id.imgStandard).alpha = 0f
            qualityPopup?.dismiss()
        }
        qualityPopup?.setOnDismissListener {
            setupQuality()
        }
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._54sdp)
        val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
        binding.rvAllSeedEggs.apply {
            adapter = shopAdapter
            layoutManager = GridLayoutManager(this@PetGardenActivity, 3)
            addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    spacingInPixelsOne,
                    spacingInPixelsTwo,
                    spacingInPixelsThree
                )
            )
        }
        val spacingInPixelsOneP = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._1sdp)
        val spacingInPixelsTwoP = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        val spacingInPixelsThreeP = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        binding.rvAllPlantPet.apply {
            if (valueBundle.equals("pet")) {
                adapter = petSelectAdapter
            } else if (valueBundle.equals("plant")) {
                adapter = plantSelectAdapter
            }
            layoutManager = GridLayoutManager(this@PetGardenActivity, 4)
            addItemDecoration(
                GridSpacingItemDecoration(
                    4,
                    spacingInPixelsOneP,
                    spacingInPixelsTwoP,
                    spacingInPixelsThreeP
                )
            )
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnSingleClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        plantSelectAdapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("plant_details", it)
            showActivity(DetailsPlantActivity::class.java, bundle)
        }
        plantSelectAdapter.onDotsClick = {

        }
        petSelectAdapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("pet_details", it)
            showActivity(DetailsPetActivity::class.java, bundle)
        }
        petSelectAdapter.onDotsClick = {

        }
        shopAdapter.onItemClick = {

        }
    }
}