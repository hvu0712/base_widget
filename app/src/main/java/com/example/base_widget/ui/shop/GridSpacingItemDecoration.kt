package com.example.base_widget.ui.shop

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int, private val spacingOne: Int, private val spacingTwo: Int, private val spacingThree: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position < spanCount) {
            outRect.top = spacingOne
        }
        if (position in spanCount..5) {
            outRect.top = spacingTwo
        }
        if (position > 5) {
            outRect.top = spacingThree
        }
    }
}

