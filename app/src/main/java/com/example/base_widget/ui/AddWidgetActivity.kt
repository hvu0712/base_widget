package com.example.base_widget.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.setOnSingleClickListener
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
    override fun inflateViewBinding() = ActivityAddWidgetBinding.inflate(layoutInflater)

    override fun initView() {
        val bundle = intent.extras
        valueBundle = bundle?.getString(ADD_WIDGET)
        if (valueBundle != null)
        {
            if (valueBundle.equals(PET)) {
                binding.ivWidget.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.iv_widget_s))
                Glide.with(this@AddWidgetActivity).asGif().centerCrop().placeholder(R.drawable.iv_animal).load(R.drawable.egg_animation).into(binding.ivWidget)
            } else {
                binding.ivWidget.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.iv_preview_plant))
                Glide.with(this@AddWidgetActivity).asGif().centerCrop().placeholder(R.drawable.iv_plant).load(R.drawable.plant_animation).into(binding.ivWidget)
            }
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnSingleClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.llSelect.setOnClickAffect {
            val intent = Intent(this, PlantPetSelectActivity::class.java)
            val bundle = Bundle()
            bundle.putString(ADD_WIDGET, valueBundle)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
        }
        binding.llTextSelect.setOnClickAffect {
            val intent = Intent(this, PlantPetSelectActivity::class.java)
            val bundle = Bundle()
            bundle.putString(ADD_WIDGET, valueBundle)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
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
                        binding.edtName.isEnabled = true
                        Toast.makeText(this,"pet",Toast.LENGTH_LONG).show()
                    }
                    is PlantModel -> {
                        binding.edtName.setText(itemSelect.name)
                        binding.edtName.isEnabled = true
                        Toast.makeText(this,"plant",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
}