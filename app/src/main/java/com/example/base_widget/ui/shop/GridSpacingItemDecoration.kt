package com.example.base_widget.ui.shop

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int, private val spacingOne: Int, private val spacingTwo: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        outRect.top = spacingOne
        if (position >= spanCount) {
            outRect.top = spacingTwo
        }
    }
}

