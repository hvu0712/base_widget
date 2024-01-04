package com.example.base_widget.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ItemShopBinding

class ShopAdapter: RecyclerView.Adapter<ShopAdapter.PetShopViewHolder>() {
    private var itemList: ArrayList<ItemCommon> = ArrayList()
    var onItemClick: ((ItemCommon) -> Unit)? = null

    fun setData(newList: ArrayList<ItemCommon>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetShopViewHolder {
        return PetShopViewHolder(
            ItemShopBinding.inflate(
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

    inner class PetShopViewHolder(private var binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemCommon) {
            binding.ivItem.setImageResource(item.image)
            binding.llGet.post {
                binding.llGet.setOnClickAffect {
                    onItemClick?.invoke(item)
                }
            }
        }

    }
}