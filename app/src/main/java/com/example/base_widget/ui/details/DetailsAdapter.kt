package com.example.base_widget.ui.details

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.disable
import com.example.base_widget.common.enable
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.common.show
import com.example.base_widget.databinding.ItemDetailsPetPlantBinding
import com.example.base_widget.ui.shop.ItemCommon
import kotlinx.coroutines.delay

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsPlantViewHolder>() {
    private var itemList: ArrayList<ItemCommon> = ArrayList()
    var onItemClick: ((ItemCommon, position: Int) -> Unit)? = null

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
                onItemClick?.invoke(item, bindingAdapterPosition)
                binding.tvTime.show()
                binding.vCountDown.show()
                it.disable()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.root.enable()
                    binding.tvTime.hide()
                    binding.vCountDown.hide()
                }, 500)
            }
            binding.tvName.text = item.name
            binding.tvTime.text = item.time.toString()
        }

    }

}