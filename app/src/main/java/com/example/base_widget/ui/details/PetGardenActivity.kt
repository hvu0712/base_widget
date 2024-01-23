package com.example.base_widget.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityPetGardenBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
import com.example.base_widget.ui.CommonDialog
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

class PetGardenActivity : BaseActivity<ActivityPetGardenBinding>(), PetSelectAdapterListener, PlantSelectAdapterListener {

    private val plantSelectAdapter = PlantSelectAdapter()
    private val petSelectAdapter = PetSelectAdapter()
    private var shopAdapter = ShopAdapter()
    private var valueBundle: String? = null
    private var detailsPopup: PopupWindow? = null
    private lateinit var popupView: View
    private var commonDialog: CommonDialog? = null
    private var appDb: AppDatabase = AppDatabase.getInstance(this)

    override fun inflateViewBinding() = ActivityPetGardenBinding.inflate(layoutInflater)
    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString(ADD_WIDGET)
        if (valueBundle != null)
        {
            when(valueBundle) {
                PET -> {
                    binding.tvAllPlantPet.text = getString(R.string.tvAllPet)
                    binding.tvSeedEggs.text = getString(R.string.tvEggs)
                    binding.tvPlantPet.text = getString(R.string.tvPet)
                    shopAdapter.setData(BaseConfig.getItemPetShop())
                    petSelectAdapter.setData(AppDatabase.getInstance(this).petDao().getAllPet())
                    petSelectAdapter.setListener(this)
                }
                PLANT -> {
                    binding.tvAllPlantPet.text = getString(R.string.tvAllPlant)
                    binding.tvSeedEggs.text = getString(R.string.tvSeed)
                    binding.tvPlantPet.text = getString(R.string.tvPlant)
                    shopAdapter.setData(BaseConfig.getItemPlantShop())
                    plantSelectAdapter.setData(AppDatabase.getInstance(this).plantDao().getAllPlant())
                    plantSelectAdapter.setListener(this)
                }
            }
        }
        setUpRecyclerView()
        setUpPopupDetails()
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
            when(valueBundle) {
                PET -> adapter = petSelectAdapter
                PLANT -> adapter = plantSelectAdapter
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

    private fun handlerAddPlant(name: String,position: Int) {
        when(position) {
            0 -> appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_rose,R.drawable.iv_rose,0L,position))
            1 -> appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_sunflower,R.drawable.iv_sunflower,0L,position))
            2 -> appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_peach_blossom,R.drawable.iv_peach_blossom,0L,position))
        }
        val plant: PlantModel = appDb.plantDao().getPlantLatest()
        showConfirmDialog(plant = plant)
    }

    private fun handlerAddPet(name: String,position: Int) {
        when(position) {
            0 -> appDb.petDao().insertPet(PetModel(null,name,R.drawable.normal_cat,R.drawable.iv_cat,getString(R.string.tvLevel1),0,position))
            1 -> appDb.petDao().insertPet(PetModel(null,name,R.drawable.normal_dog,R.drawable.iv_dog,getString(R.string.tvLevel1),0,position))
            2 -> appDb.petDao().insertPet(PetModel(null,name,R.drawable.normal_rabbit,R.drawable.iv_rabbit,getString(R.string.tvLevel1),0,position))
        }
        val pet: PetModel = appDb.petDao().getPetLatest()
        showConfirmDialog(pet = pet)
    }

    private fun showConfirmDialog(pet: PetModel? = null, plant: PlantModel? = null) {
        commonDialog = CommonDialog(this,true)
        when(valueBundle) {
            PET -> commonDialog!!.setUpDialog(pet!!.imagePlaceHolder,pet.name)
            PLANT -> commonDialog!!.setUpDialog(plant!!.imagePlaceHolder,plant.name)
        }
        commonDialog!!.setListener(object : CommonDialog.Listener {
            override fun confirm() {

                commonDialog?.dismiss()
            }

            override fun close() {
                commonDialog?.dismiss()
            }

        })
        commonDialog!!.showDialog()
    }

    private fun addPet(position: Int) {
        val lastIndex = appDb.petDao().getAllPet().lastIndex
        val name = "Pet ${lastIndex + 2}"
        when (position) {
            0 ->  handlerAddPet(name,position)
            1 ->  handlerAddPet(name,position)
            2 ->  handlerAddPet(name,position)
        }
        petSelectAdapter.setData(appDb.petDao().getAllPet())
    }

    private fun addPlant(position: Int) {
        val lastIndex = appDb.plantDao().getAllPlant().lastIndex
        val name = "Plant ${lastIndex + 2}"
        when (position) {
            0 ->  handlerAddPlant(name,position)
            1 ->  handlerAddPlant(name,position)
            2 ->  handlerAddPlant(name,position)
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

    override fun setPetOnClickListener(it: View, pos: Int, item: PetModel) {
        showPopupDetails(it)
        setUpListenerPopup(pos, pet = item)
    }

    override fun setPlantOnClickListener(it: View, pos: Int, item: PlantModel) {
        showPopupDetails(it)
        setUpListenerPopup(pos, plant = item)
    }
    private fun setUpListenerPopup(pos: Int,pet: PetModel? = null, plant: PlantModel? = null) {
        popupView.findViewById<View>(R.id.tvRename).setOnClickListener {
            when(valueBundle)
            {
                PET -> showRenameDialog(pet = pet)
                PLANT -> showRenameDialog(plant = plant)
            }
            detailsPopup?.dismiss()
        }
        popupView.findViewById<View>(R.id.tvDelete).setOnClickListener {
            when(valueBundle)
            {
                PET -> {
                    appDb.petDao().deletePet(pet!!)
                    petSelectAdapter.setData(appDb.petDao().getAllPet())
                }
                PLANT -> {
                    appDb.plantDao().deletePlant(plant!!)
                    plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
                }
            }
            detailsPopup?.dismiss()
        }
        popupView.setOnClickListener {
            detailsPopup?.dismiss()
        }
        detailsPopup?.setOnDismissListener {
            when(valueBundle)
            {
                PET -> petSelectAdapter.setData(appDb.petDao().getAllPet())
                PLANT -> plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
            }
        }
    }

    private fun showRenameDialog(pet: PetModel? = null, plant: PlantModel? = null) {
        commonDialog = CommonDialog(this,false)
        when(valueBundle) {
            PET -> commonDialog!!.setUpDialog(pet!!.imagePlaceHolder,pet.name)
            PLANT -> commonDialog!!.setUpDialog(plant!!.imagePlaceHolder,plant.name)
        }
        commonDialog!!.setListener(object : CommonDialog.Listener {
            override fun confirm() {
                when(valueBundle) {
                    PET -> {
                        appDb.petDao().updatePetName(pet?.id,commonDialog!!.getEditTextValue())
                        petSelectAdapter.setData(appDb.petDao().getAllPet())
                    }
                    PLANT -> {
                        appDb.plantDao().updatePlantName(plant?.id,commonDialog!!.getEditTextValue())
                        plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
                    }
                }
                commonDialog?.dismiss()
            }

            override fun close() {
                commonDialog?.dismiss()
            }

        })
        commonDialog!!.showDialog()
    }

    private fun showPopupDetails(view: View) {
        val anchorView = IntArray(2)
        view.getLocationInWindow(anchorView)
        detailsPopup?.showAtLocation(
            view, Gravity.NO_GRAVITY,
            anchorView[0] + view.width / 2,
            anchorView[1] + view.height - 20
        )
    }

    private fun setUpPopupDetails() {
        popupView = LayoutInflater.from(this).inflate(R.layout.popup_details, null)
        detailsPopup = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        detailsPopup?.elevation = 10f
    }

}