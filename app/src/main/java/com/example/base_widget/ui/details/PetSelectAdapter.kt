package com.example.base_widget.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.base_widget.R
import com.example.base_widget.databinding.ItemAllSelectBinding
import com.example.base_widget.model.PetModel

class PetSelectAdapter : RecyclerView.Adapter<PetSelectAdapter.PetSelectViewHolder>() {
    private var itemList: MutableList<PetModel> = mutableListOf()
    var onItemClick: ((PetModel) -> Unit)? = null
    private var listener: PetSelectAdapterListener? = null
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: MutableList<PetModel>) {
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
            binding.ivItem.setImageResource(item.imagePlaceHolder)
            binding.tvName.text = item.name
            binding.tvLevel.text = item.level
            binding.ccItem.post {
                binding.ccItem.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                binding.ivDots.setOnClickListener {
                    listener?.setPetOnClickListener(it,bindingAdapterPosition,item)
                }
            }
        }

    }

    fun setListener(listener: PetSelectAdapterListener) {
        this.listener = listener
    }

}

interface PetSelectAdapterListener {
    fun setPetOnClickListener(it: View, pos: Int, item: PetModel)
}