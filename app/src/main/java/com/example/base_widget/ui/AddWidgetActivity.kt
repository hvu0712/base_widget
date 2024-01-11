package com.example.base_widget.ui

import android.content.Intent
import android.os.Bundle
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

class AddWidgetActivity : BaseActivity<ActivityAddWidgetBinding>(){

    private var valueBundle: String? = null
    override fun inflateViewBinding() = ActivityAddWidgetBinding.inflate(layoutInflater)

    override fun initView() {
        Glide.with(this@AddWidgetActivity).asGif().centerCrop().placeholder(R.drawable.iv_plant).load(R.drawable.plant_animation).into(binding.ivWidget)
        Glide.with(this@AddWidgetActivity).asGif().centerCrop().placeholder(R.drawable.iv_animal).load(R.drawable.egg_animation).into(binding.ivWidget)
        val bundle = intent.extras
        valueBundle = bundle?.getString("addWidget")
        if (valueBundle.equals("pet")) {
            binding.ivWidget.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.iv_widget_s))
        } else if (valueBundle.equals("plant")) {
            binding.ivWidget.setImageDrawable(AppCompatResources.getDrawable(this,R.drawable.iv_preview_plant))
        }
    }

    override fun setUpListener() {
        binding.ivBack.setOnSingleClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.llSelect.setOnClickAffect {
            val intent = Intent(this, PlantPetSelectActivity::class.java)
            val bundle = Bundle()
            bundle.putString("addWidget", valueBundle)
            intent.putExtras(bundle ?: Bundle())
            resultLauncher.launch(intent)
        }
        binding.llTextSelect.setOnClickAffect {
            val intent = Intent(this, PlantPetSelectActivity::class.java)
            val bundle = Bundle()
            bundle.putString("addWidget", valueBundle)
            intent.putExtras(bundle ?: Bundle())
            resultLauncher.launch(intent)
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // get data
                val data: Intent? = result.data
                when(val itemSelect = data?.getSerializableExtra("item_select"))
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