package com.example.base_widget.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.util.*

var toast: Toast? = null
fun Context.showToast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast!!.show()
}

fun Context.showToast(message: Int) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast!!.show()
}


@SuppressLint("ClickableViewAccessibility")
fun View.setOnClickAffect(onClick: ((View?) -> Unit)) {
    this.setOnTouchListener { v, motionEvent ->
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                v?.alpha = 0.5f
            }

            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                v?.alpha = 1f
            }
        }
        false
    }
    this.setOnClickListener {
        onClick?.invoke(it)
    }
}

@SuppressLint("ClickableViewAccessibility")
fun View.setOnClickAffect(onClick: OnClickListener) {
    this.setOnTouchListener { v, motionEvent ->
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                v?.alpha = 0.5f
            }

            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                v?.alpha = 1f
            }
        }
        false
    }
    this.setOnClickListener(onClick)
}

fun Dialog.initLayout(context: Context, percentWidth: Float = 0.8f) {
    window?.setBackgroundDrawableResource(android.R.color.transparent)
    val w = (context.resources.displayMetrics.widthPixels * percentWidth).toInt()
    val h = ViewGroup.LayoutParams.WRAP_CONTENT
    window!!.setLayout(w, h)
    setCancelable(false)
}

fun View.setOnSingleClickListener(onClick: ((View?) -> Unit)? = null) {
    val listener = SafeOnClickListener()
    listener.onSafeClick = onClick
    this.setOnClickAffect(listener)
}

fun View.setClickListener(onClick: ((View?) -> Unit)? = null) {
    val listener = SafeOnClickListener()
    listener.onSafeClick = onClick
    this.setOnClickListener(listener)
}

fun Activity.hideKeyboard() {
    val inputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun Activity.showKeyboard(editText: View) {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.enable() {
    this?.isEnabled = true
}

fun View?.disable() {
    this?.isEnabled = false
}

