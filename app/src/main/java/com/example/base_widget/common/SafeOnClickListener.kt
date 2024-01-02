package com.example.base_widget.common

import android.os.SystemClock
import android.view.View

open class SafeOnClickListener : View.OnClickListener {
    private var lastClickTime = 0L

    var onSafeClick: ((View?) -> Unit)? = null

    override fun onClick(p0: View?) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        onSafeClick?.invoke(p0)

    }
}