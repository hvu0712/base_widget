package com.example.base_widget.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.enable
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityAddWidgetBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
import com.example.base_widget.ui.details.PlantPetSelectActivity
import com.example.base_widget.utils.BaseConfig.ADD_WIDGET
import com.example.base_widget.utils.BaseConfig.ITEM_SELECT
import com.example.base_widget.utils.BaseConfig.PET
import com.example.base_widget.utils.BaseConfig.PLANT

class AddWidgetActivity : BaseActivity<ActivityAddWidgetBinding>(){

    private var valueBundle: String? = null
    private var appDb: AppDatabase = AppDatabase.getInstance(this)
    private var previousText: String? = null
    override fun inflateViewBinding() = ActivityAddWidgetBinding.inflate(layoutInflater)

    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString(ADD_WIDGET)
        if (valueBundle != null)
        {
            when(valueBundle) {
                PET -> {
                    binding.ivWidget.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.iv_widget_s))
                    Glide.with(this@AddWidgetActivity).asGif().centerCrop().placeholder(R.drawable.iv_animal).load(R.drawable.egg_animation).into(binding.ivWidget)
                }

                PLANT -> {
                    binding.ivWidget.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.iv_preview_plant))
                    Glide.with(this@AddWidgetActivity).asGif().centerCrop().placeholder(R.drawable.iv_plant).load(R.drawable.plant_animation).into(binding.ivWidget)
                }
            }
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnSingleClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.llSelect.setOnClickAffect {
            binding.edtName.clearFocus()
            val intent = Intent(this, PlantPetSelectActivity::class.java)
            val bundle = Bundle()
            bundle.putString(ADD_WIDGET, valueBundle)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
        }
        binding.llTextSelect.setOnClickAffect {
            binding.edtName.clearFocus()
            val intent = Intent(this, PlantPetSelectActivity::class.java)
            val bundle = Bundle()
            bundle.putString(ADD_WIDGET, valueBundle)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
        }
        binding.llAddWidget.setOnClickAffect {

        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // get data
                val data: Intent? = result.data
                when(val itemSelect = data?.getSerializableExtra(ITEM_SELECT))
                {
                    is PetModel -> {
                        binding.edtName.setText(itemSelect.name)
                        binding.edtName.enable()
                        addTextWatcher(binding.edtName, pet = itemSelect)
                    }
                    is PlantModel -> {
                        binding.edtName.setText(itemSelect.name)
                        binding.edtName.enable()
                        addTextWatcher(binding.edtName, plant = itemSelect)
                    }
                }

            }
        }

    private fun addTextWatcher(editText: EditText, pet: PetModel? = null, plant: PlantModel? = null) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                previousText = charSequence?.toString()
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                val newText = editable?.toString()
                if (newText != previousText) {
                    if (pet != null) {
                        appDb.petDao().updatePetName(pet.id, newText ?: pet.name)
                    }
                    if (plant != null) {
                        appDb.plantDao().updatePlantName(plant.id, newText ?: plant.name)
                    }
                    previousText = newText
                }
            }
        })
    }
}