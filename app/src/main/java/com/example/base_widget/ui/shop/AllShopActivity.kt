package com.example.base_widget.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ActivityAllShopBinding
import com.example.base_widget.model.PetModel
import com.example.base_widget.model.PlantModel
import com.example.base_widget.ui.CommonDialog
import com.example.base_widget.ui.details.PetSelectAdapter
import com.example.base_widget.ui.details.PlantSelectAdapter
import com.example.base_widget.utils.BaseConfig.PET
import com.example.base_widget.utils.BaseConfig.PLANT

class AllShopActivity : BaseActivity<ActivityAllShopBinding>(), ShopFragmentListener {

    private val plantSelectAdapter = PlantSelectAdapter()
    private val petSelectAdapter = PetSelectAdapter()
    private var commonDialog: CommonDialog? = null
    private var appDb: AppDatabase = AppDatabase.getInstance(this)
    override fun inflateViewBinding() = ActivityAllShopBinding.inflate(layoutInflater)

    override fun initView() {
        binding.viewPager.adapter = MyViewPagerAdapter()
        binding.viewPager.registerOnPageChangeCallback(viewPagerListener)
    }

    override fun setUpListener() {
        binding.ivBack.setOnClickAffect {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.llPlant.setOnClickListener {
            binding.viewPager.currentItem = 1
        }
        binding.llPlantDisable.setOnClickListener {
            binding.viewPager.currentItem = 1
        }
        binding.llPet.setOnClickListener {
            binding.viewPager.currentItem = 0
        }
        binding.llPetDisable.setOnClickListener {
            binding.viewPager.currentItem = 0
        }
    }

    private fun addPet(position: Int) {
        val lastIndex = appDb.petDao().getAllPet().lastIndex
        val name = "Pet ${lastIndex + 2}"
        when (position) {
            0 ->  handlerAddPet(name,position)
            1 ->  appDb.petDao().insertPet(PetModel(null,name,R.drawable.normal_dog,R.drawable.iv_dog,getString(R.string.tvLevel1),0,position))
            2 ->  appDb.petDao().insertPet(PetModel(null,name,R.drawable.normal_rabbit,R.drawable.iv_rabbit,getString(R.string.tvLevel1),0,position))
        }
        petSelectAdapter.setData(appDb.petDao().getAllPet())
    }

    private fun handlerAddPlant(name: String,position: Int) {
        appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_rose,R.drawable.iv_rose,0L,position))
        val pet: PetModel = appDb.petDao().getPetLatest()
        showConfirmDialog(pet = pet)
    }

    private fun handlerAddPet(name: String,position: Int) {
        appDb.petDao().insertPet(PetModel(null,name,R.drawable.normal_cat,R.drawable.iv_cat,getString(R.string.tvLevel1),0,position))
        val pet: PetModel = appDb.petDao().getPetLatest()
        showConfirmDialog(pet = pet)
    }

    private fun showConfirmDialog(pet: PetModel? = null, plant: PlantModel? = null) {
        commonDialog = CommonDialog(this,false)
        when(binding.viewPager.currentItem) {
            0 -> commonDialog!!.setUpDialog(pet!!.imagePlaceHolder,pet.name)
            1 -> commonDialog!!.setUpDialog(plant!!.imagePlaceHolder,plant.name)
        }
        commonDialog!!.setListener(object : CommonDialog.Listener {
            override fun confirm() {
                when(binding.viewPager.currentItem) {
                    0 -> {
                        appDb.petDao().updatePetName(pet?.id,commonDialog!!.getEditTextValue())
                        petSelectAdapter.setData(appDb.petDao().getAllPet())
                    }
                    1 -> {
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

    private fun addPlant(position: Int) {
        val lastIndex = appDb.plantDao().getAllPlant().lastIndex
        val name = "Plant ${lastIndex + 2}"
        when (position) {
            0 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_rose,R.drawable.iv_rose,0L,position))
            1 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_sunflower,R.drawable.iv_sunflower,0L,position))
            2 ->  appDb.plantDao().insertPlant(PlantModel(null,name,R.drawable.iv_peach_blossom,R.drawable.iv_peach_blossom,0L,position))
        }
        plantSelectAdapter.setData(appDb.plantDao().getAllPlant())
    }

    private var viewPagerListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> {
                    binding.llPet.show()
                    binding.llPetDisable.hide()
                    binding.llPlantDisable.show()
                    binding.llPlant.hide()
                }

                1 -> {
                    binding.llPet.hide()
                    binding.llPetDisable.show()
                    binding.llPlantDisable.hide()
                    binding.llPlant.show()
                }
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class MyViewPagerAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("page", position)
            return ShopFragment().apply {
                arguments = bundle
                setListener(this@AllShopActivity)}
        }
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerListener)
        super.onDestroy()
    }

    override fun setOnClickListener(it: ItemTraining, pos: Int) {
        when (it.type)
        {
            PET -> addPet(pos)
            PLANT -> addPlant(pos)
        }
    }
}