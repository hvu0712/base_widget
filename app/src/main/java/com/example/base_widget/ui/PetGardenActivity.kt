package com.example.base_widget.ui

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityPetGardenBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
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
    private var appDb: AppDatabase = AppDatabase.getInstance(this)

    override fun inflateViewBinding() = ActivityPetGardenBinding.inflate(layoutInflater)
    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString("addWidget")
        if (valueBundle != null)
        {
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
        }
        setUpRecyclerView()
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
            val bundle = Bundle()
            bundle.putSerializable("plant_details", it)
            showActivity(DetailsPlantActivity::class.java, bundle)
        }
        petSelectAdapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("pet_details", it)
            showActivity(DetailsPetActivity::class.java, bundle)
        }
        shopAdapter.onItemClick = { it, pos ->
            when (it.type)
            {
                "pet" -> addPet(pos)
                "plant" -> addPlant(pos)
            }
        }
    }

    private fun addPet(position: Int)
    {
        val lastIndex = if (appDb.petDao().getAllPet().isEmpty()) {
            0
        } else {
            appDb.petDao().getAllPet().lastIndex
        }
        val name = "Pet ${lastIndex + 1}"
        when(position)
        {
            0 ->  appDb.petDao().insertPet(PetModel(null,name,R.drawable.iv_cat,getString(R.string.tvLevel1),0))
            1 ->  appDb.petDao().insertPet(PetModel(null,name,R.drawable.iv_dog,getString(R.string.tvLevel1),0))
            2 ->  appDb.petDao().insertPet(PetModel(null,name,R.drawable.iv_rabbit,getString(R.string.tvLevel1),0))
        }
        petSelectAdapter.setData(appDb.petDao().getAllPet())
    }

    private fun addPlant(position: Int)
    {
        val lastIndex = if (appDb.plantDao().getAllPlant().isEmpty()) {
            0
        } else {
            appDb.plantDao().getAllPlant().lastIndex
        }
        val name = "Plant ${lastIndex + 1}"
        when(position)
        {
            0 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_plant_all_select,0L))
            1 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_plant_all_select,0L))
            2 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_plant_all_select,0L))
        }
        plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
    }
}