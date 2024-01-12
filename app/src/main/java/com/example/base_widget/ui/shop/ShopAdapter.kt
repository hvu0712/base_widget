package com.example.base_widget.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ItemShopBinding

class ShopAdapter: RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {
    private var itemList: ArrayList<ItemTraining> = ArrayList()
    var onItemClick: ((ItemTraining, position: Int) -> Unit)? = null

    fun setData(newList: ArrayList<ItemTraining>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            ItemShopBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class ShopViewHolder(private var binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemTraining) {
            binding.ivItem.setImageResource(item.image)
            binding.llGet.post {
                binding.llGet.setOnClickAffect {
                    onItemClick?.invoke(item,bindingAdapterPosition)
                }
            }
        }

    }
}