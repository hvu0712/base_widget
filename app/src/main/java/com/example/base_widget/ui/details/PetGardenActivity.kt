package com.example.base_widget.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityPetGardenBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
import com.example.base_widget.ui.shop.GridSpacingItemDecoration
import com.example.base_widget.ui.shop.ShopAdapter
import com.example.base_widget.utils.BaseConfig
import com.example.base_widget.utils.BaseConfig.ADD_WIDGET
import com.example.base_widget.utils.BaseConfig.PET
import com.example.base_widget.utils.BaseConfig.PET_DETAILS
import com.example.base_widget.utils.BaseConfig.PLANT
import com.example.base_widget.utils.BaseConfig.PLANT_DETAILS
import com.example.base_widget.utils.BaseConfig.UPDATE
import com.example.base_widget.utils.BaseConfig.UPDATE_LIST

class PetGardenActivity : BaseActivity<ActivityPetGardenBinding>() {

    private val plantSelectAdapter = PlantSelectAdapter()
    private val petSelectAdapter = PetSelectAdapter()
    private var shopAdapter = ShopAdapter()
    private var valueBundle: String? = null
    private var appDb: AppDatabase = AppDatabase.getInstance(this)

    override fun inflateViewBinding() = ActivityPetGardenBinding.inflate(layoutInflater)
    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString(ADD_WIDGET)
        if (valueBundle != null)
        {
            if (valueBundle.equals(PET)) {
                binding.tvAllPlantPet.text = getString(R.string.tvAllPet)
                binding.tvSeedEggs.text = getString(R.string.tvEggs)
                binding.tvPlantPet.text = getString(R.string.tvPet)
                shopAdapter.setData(BaseConfig.getItemPetShop())
                petSelectAdapter.setData(AppDatabase.getInstance(this).petDao().getAllPet())
            } else {
                binding.tvAllPlantPet.text = getString(R.string.tvAllPlant)
                binding.tvSeedEggs.text = getString(R.string.tvSeed)
                binding.tvPlantPet.text = getString(R.string.tvPlant)
                shopAdapter.setData(BaseConfig.getItemPlantShop())
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
            if (valueBundle.equals(PET)) {
                adapter = petSelectAdapter
            } else if (valueBundle.equals(PLANT)) {
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
            val intent = Intent(this, DetailsPlantActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(PLANT_DETAILS, it)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
        }
        petSelectAdapter.onItemClick = {
            val intent = Intent(this, DetailsPetActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(PET_DETAILS, it)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
        }
        shopAdapter.onItemClick = { it, pos ->
            when (it.type)
            {
                PET -> addPet(pos)
                PLANT -> addPlant(pos)
            }
        }
    }

    private fun addPet(position: Int)
    {
        val lastIndex = appDb.petDao().getAllPet().lastIndex
        val name = "Pet ${lastIndex + 2}"
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
        val lastIndex = appDb.plantDao().getAllPlant().lastIndex
        val name = "Plant ${lastIndex + 2}"
        when(position)
        {
            0 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_rose,0L))
            1 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_sunflower,0L))
            2 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_peach_blossom,0L))
        }
        plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // get data
                val data: Intent? = result.data
                val updateList = data?.getSerializableExtra(UPDATE_LIST)
                if(updateList != null && updateList == UPDATE)
                {
                    petSelectAdapter.setData(appDb.petDao().getAllPet())
                    plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
                }
            }
        }
}