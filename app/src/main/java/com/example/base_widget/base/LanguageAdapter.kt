package com.example.base_widget.base

import android.annotation.SuppressLint
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.base_widget.R
import com.example.base_widget.common.SharePrefUtils

class LanguageAdapter :
    BaseQuickAdapter<ItemLanguage, BaseViewHolder>(R.layout.layout_item_language) {
    var selectedLang: String = SharePrefUtils.language
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun convert(holder: BaseViewHolder, item: ItemLanguage) {
        holder.setText(R.id.tvLanguage, item.name)
            .setImageResource(R.id.ivAvatar, item.flagIcon)
        holder.getView<ImageView>(R.id.checked).isActivated = selectedLang == item.code
    }
}