package com.example.base_widget.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_widget.databinding.ItemAllSelectBinding
import com.example.base_widget.model.PetModel

class PetSelectAdapter : RecyclerView.Adapter<PetSelectAdapter.PetSelectViewHolder>() {
    private var itemList: ArrayList<PetModel> = ArrayList()
    var onItemClick: ((PetModel) -> Unit)? = null
    var onDotsClick: ((PetModel) -> Unit)? = null
    fun setData(newList: ArrayList<PetModel>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetSelectViewHolder {
        return PetSelectViewHolder(
            ItemAllSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PetSelectViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class PetSelectViewHolder(private var binding: ItemAllSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PetModel) {
            binding.ivItem.setImageResource(item.image)
            binding.tvName.text = item.name
            binding.tvLevel.text = item.level
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