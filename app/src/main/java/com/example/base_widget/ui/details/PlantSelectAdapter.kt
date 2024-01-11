package com.example.base_widget.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.hide
import com.example.base_widget.databinding.ItemAllSelectBinding
import com.example.base_widget.model.PlantModel


class PlantSelectAdapter : RecyclerView.Adapter<PlantSelectAdapter.PlantSelectViewHolder>() {
    private var itemList: ArrayList<PlantModel> = ArrayList()
    var onItemClick: ((PlantModel) -> Unit)? = null
    var onDotsClick: ((PlantModel) -> Unit)? = null

    fun setData(newList: ArrayList<PlantModel>) {
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
            )
        )
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
                    onDotsClick?.invoke(item)
                }
            }
        }
    }

}