package com.example.base_widget.ui.details

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityPlantPetSelectBinding
import com.example.base_widget.ui.shop.GridSpacingItemDecoration
import com.example.base_widget.utils.AppUtils

class PlantPetSelectActivity: BaseActivity<ActivityPlantPetSelectBinding>() {

    private val petSelectAdapter = PetSelectAdapter()
    private val plantSelectAdapter = PlantSelectAdapter()
    private var valueBundle: String? = null
    override fun inflateViewBinding() = ActivityPlantPetSelectBinding.inflate(layoutInflater)

    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString("addWidget")
        if (valueBundle.equals("pet")) {
            binding.tvAllPlant.text = getString(R.string.tvAllPet)
            binding.tvName.text = getString(R.string.tvPet)
            petSelectAdapter.setData(AppDatabase.getInstance(this).petDao().getAllPet())
        } else if (valueBundle.equals("plant")) {
            binding.tvAllPlant.text = getString(R.string.tvAllPlant)
            binding.tvName.text = getString(R.string.tvPlant)
            plantSelectAdapter.setData(AppDatabase.getInstance(this).plantDao().getAllPlant())
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._42sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        binding.rvAllPlantPet.apply {
            if (valueBundle.equals("pet"))
            {
                adapter = petSelectAdapter
            }
            else if (valueBundle.equals("plant"))
            {
                adapter = plantSelectAdapter
            }
            layoutManager = GridLayoutManager(this@PlantPetSelectActivity, 4)
            addItemDecoration(GridSpacingItemDecoration(4,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnSingleClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        petSelectAdapter.onItemClick = {
            val resultIntent = Intent()
            resultIntent.putExtra("item_select",it)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        plantSelectAdapter.onItemClick = {
            val resultIntent = Intent()
            resultIntent.putExtra("item_select",it)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}