package com.example.base_widget.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ItemPetShopBinding

class PetShopAdapter: RecyclerView.Adapter<PetShopAdapter.PetShopViewHolder>() {
    private var itemList: ArrayList<ItemCommon> = ArrayList()
    var onItemClick: ((ItemCommon) -> Unit)? = null

    fun setData(newList: ArrayList<ItemCommon>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetShopViewHolder {
        return PetShopViewHolder(
            ItemPetShopBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PetShopViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class PetShopViewHolder(private var binding: ItemPetShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemCommon) {
            binding.ivItem.setImageResource(item.image)
            binding.btnGet.setOnClickAffect {
                onItemClick?.invoke(item)
            }
        }

    }
}