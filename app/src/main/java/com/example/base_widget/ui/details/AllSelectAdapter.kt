package com.example.base_widget.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.common.setOnClickAffect
import com.example.base_widget.databinding.ItemAllSelectBinding


class AllSelectAdapter : RecyclerView.Adapter<AllSelectAdapter.AllSelectViewHolder>() {
    private var itemList: ArrayList<ItemSelect> = ArrayList()
    var onItemClick: ((ItemSelect) -> Unit)? = null

    fun setData(newList: ArrayList<ItemSelect>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllSelectViewHolder {
        return AllSelectViewHolder(
            ItemAllSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllSelectViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class AllSelectViewHolder(private var binding: ItemAllSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemSelect) {
            binding.ivItem.setImageResource(item.image)
            binding.tvName.text = item.name
            binding.tvLevel.text = item.level
            binding.ccItem.post {
                binding.ccItem.setOnClickAffect {
                    onItemClick?.invoke(item)
                }
            }
        }

    }

}