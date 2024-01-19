package com.example.base_widget.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.R
import com.example.base_widget.common.hide
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.databinding.ItemAllSelectBinding
import com.example.base_widget.model.PlantModel
import com.example.base_widget.ui.shop.ItemTraining
import com.example.base_widget.ui.shop.ShopFragmentListener


class PlantSelectAdapter : RecyclerView.Adapter<PlantSelectAdapter.PlantSelectViewHolder>() {
    private var itemList: MutableList<PlantModel> = mutableListOf()
    var onItemClick: ((PlantModel) -> Unit)? = null
    private var listener: PlantSelectAdapterListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: MutableList<PlantModel>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantSelectViewHolder {
        return PlantSelectViewHolder(
            ItemAllSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: PlantSelectViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class PlantSelectViewHolder(private var binding: ItemAllSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlantModel) {
            binding.ivItem.setImageResource(item.image)
            binding.tvName.text = item.name
            binding.tvLevel.hide()
            binding.ccItem.post {
                binding.ccItem.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                binding.ivDots.setOnClickListener {
                    listener?.setPlantOnClickListener(it,bindingAdapterPosition)
                }
            }
        }

    }



    fun setListener(listener: PlantSelectAdapterListener) {
        this.listener = listener
    }

}

interface PlantSelectAdapterListener {
    fun setPlantOnClickListener(it: View, pos: Int)
}