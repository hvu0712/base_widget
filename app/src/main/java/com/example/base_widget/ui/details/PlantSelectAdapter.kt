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
import com.example.base_widget.R
import com.example.base_widget.common.hide
import com.example.base_widget.databinding.ItemAllSelectBinding
import com.example.base_widget.model.PlantModel


class PlantSelectAdapter : RecyclerView.Adapter<PlantSelectAdapter.PlantSelectViewHolder>() {
    private var itemList: MutableList<PlantModel> = mutableListOf()
    var onItemClick: ((PlantModel) -> Unit)? = null
    private var detailsPopup: PopupWindow? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: MutableList<PlantModel>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantSelectViewHolder {
        val context = parent.context
        val viewHolder = PlantSelectViewHolder(
            ItemAllSelectBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
        // Gọi hàm setUpPopupDetails ở đây
        viewHolder.setUpPopupDetails(context)
        return viewHolder
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
                    showPopupDetails(binding.ivDots)
                }
            }
        }

        fun setUpPopupDetails(context: Context) {
            val popupView = LayoutInflater.from(context).inflate(R.layout.popup_details, null)
            detailsPopup = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            detailsPopup?.elevation = 10f
            popupView.findViewById<View>(R.id.tvRename).setOnClickListener {
                Toast.makeText(context,"", Toast.LENGTH_LONG).show()
                detailsPopup?.dismiss()
            }
            popupView.findViewById<View>(R.id.tvDelete).setOnClickListener {
                Toast.makeText(context,"", Toast.LENGTH_LONG).show()
                detailsPopup?.dismiss()
            }
            detailsPopup?.setOnDismissListener {

            }
        }
    }

    private fun showPopupDetails(ivDots: ImageView) {
        val anchorView = IntArray(2)
        ivDots.getLocationInWindow(anchorView)
        detailsPopup?.showAtLocation(
            ivDots, Gravity.NO_GRAVITY,
            anchorView[0] + ivDots.width / 2,
            anchorView[1] + ivDots.height - 20
        )
    }

}