package com.example.base_widget.ui.details

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityPlantPetSelectBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
import com.example.base_widget.ui.CommonDialog
import com.example.base_widget.ui.shop.GridSpacingItemDecoration
import com.example.base_widget.utils.BaseConfig.ADD_WIDGET
import com.example.base_widget.utils.BaseConfig.ITEM_SELECT
import com.example.base_widget.utils.BaseConfig.PET
import com.example.base_widget.utils.BaseConfig.PLANT

class PlantPetSelectActivity: BaseActivity<ActivityPlantPetSelectBinding>(), PetSelectAdapterListener, PlantSelectAdapterListener {

    private val petSelectAdapter = PetSelectAdapter()
    private val plantSelectAdapter = PlantSelectAdapter()
    private var valueBundle: String? = null
    private var detailsPopup: PopupWindow? = null
    private lateinit var popupView: View
    private var commonDialog: CommonDialog? = null
    private var appDb: AppDatabase = AppDatabase.getInstance(this)

    override fun inflateViewBinding() = ActivityPlantPetSelectBinding.inflate(layoutInflater)

    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString(ADD_WIDGET)
        if (valueBundle != null)
        {
            when(valueBundle) {
                PET -> {
                    binding.tvAllPlant.text = getString(R.string.tvAllPet)
                    binding.tvName.text = getString(R.string.tvPet)
                    petSelectAdapter.setData(appDb.petDao().getAllPet())
                    petSelectAdapter.setListener(this)
                }
                PLANT -> {
                    binding.tvAllPlant.text = getString(R.string.tvAllPlant)
                    binding.tvName.text = getString(R.string.tvPlant)
                    plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
                    plantSelectAdapter.setListener(this)
                }
            }
        }
        setUpRecyclerView()
        setUpPopupDetails()
    }

    private fun setUpRecyclerView() {
        val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._42sdp)
        val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._15sdp)
        binding.rvAllPlantPet.apply {
            when(valueBundle) {
                PET -> adapter = petSelectAdapter
                PLANT -> adapter = plantSelectAdapter
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
            resultIntent.putExtra(ITEM_SELECT,it)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        plantSelectAdapter.onItemClick = {
            val resultIntent = Intent()
            resultIntent.putExtra(ITEM_SELECT,it)
            setResult(RESULT_OK, resultIntent)
            finish()
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
                    appDb.petDao().deletePet(pos+1)
                    petSelectAdapter.setData(appDb.petDao().getAllPet())
                }
                PLANT -> {
                    appDb.plantDao().deletePlant(pos+1)
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