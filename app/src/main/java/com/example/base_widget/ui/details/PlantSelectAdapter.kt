package com.example.base_widget.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.show
import com.example.base_widget.databinding.ItemAllSelectBinding
import com.example.base_widget.model.PlantModel


class PlantSelectAdapter : RecyclerView.Adapter<PlantSelectAdapter.PlantSelectViewHolder>() {
    private var itemList: MutableList<PlantModel> = mutableListOf()
    var onItemClick: ((PlantModel) -> Unit)? = null
    var onDotsClick: ((view:View,item: PlantModel) -> Unit)? = null

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
            binding.tvLevel.text = item.level
            binding.tvLevel.show()
            binding.ccItem.post {
                binding.ccItem.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                binding.ivDots.setOnClickListener {
                    onDotsClick?.invoke(it, item)
                }
            }
        }

    }


}