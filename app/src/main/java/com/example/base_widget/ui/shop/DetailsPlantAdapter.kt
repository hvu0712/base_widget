package com.example.base_widget.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ItemDetailsPlantBinding

class DetailsPlantAdapter : RecyclerView.Adapter<DetailsPlantAdapter.DetailsPlantViewHolder>() {
    private var itemList: ArrayList<ItemCommon> = ArrayList()
    var onItemClick: ((ItemCommon) -> Unit)? = null

    fun setData(newList: ArrayList<ItemCommon>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPlantViewHolder {
        return DetailsPlantViewHolder(
            ItemDetailsPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsPlantViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class DetailsPlantViewHolder(private var binding: ItemDetailsPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemCommon) {
            binding.ivItem.setImageResource(item.image)
            binding.llGet.setOnClickAffect {
                onItemClick?.invoke(item)
            }
        }

    }

}