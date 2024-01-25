package com.example.base_widget.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.disable
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.databinding.ItemDetailsPetPlantBinding
import com.example.base_widget.ui.shop.ItemCommon

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsPlantViewHolder>() {
    private var itemList: ArrayList<ItemCommon> = ArrayList()
    var onItemClick: ((ItemCommon, position: Int, binding: ItemDetailsPetPlantBinding) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<ItemCommon>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPlantViewHolder {
        return DetailsPlantViewHolder(
            ItemDetailsPetPlantBinding.inflate(
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

    inner class DetailsPlantViewHolder(private var binding: ItemDetailsPetPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemCommon) {
            binding.ivItem.setImageResource(item.image)
            binding.root.setOnClickAffect {
                onItemClick?.invoke(item, bindingAdapterPosition, binding)
                binding.tvTime.show()
                binding.vCountDown.show()
                it.disable()
            }
            binding.tvName.text = item.name
            binding.tvTime.text = item.time.toString()
        }

    }
}


