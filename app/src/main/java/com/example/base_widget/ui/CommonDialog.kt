package com.example.base_widget.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import com.example.base_widget.R
import com.example.base_widget.databinding.DialogConfirmBinding
import com.example.base_widget.databinding.DialogRenameBinding

class CommonDialog(private val context: Context,private val isConfirmDialog: Boolean) : Dialog(
    context, R.style.BaseDialog
) {
    private val cBinding = DialogConfirmBinding.inflate(layoutInflater)
    private val rBinding = DialogRenameBinding.inflate(layoutInflater)


    private var listener: Listener? = null

    init {
        if (isConfirmDialog) {
            setContentView(cBinding.root)
        } else {
            setContentView(rBinding.root)
        }

        val attributes = window!!.attributes
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = attributes
        window!!.setSoftInputMode(16)
        onclick()
        changeRating()
    }

    interface Listener {
        fun send()
        fun rating()
        fun later()
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    private fun changeRating() {

    }

    private fun onclick() {
        binding.btnRate.setOnClickListener {
            if (binding.rtb.rating == 0f) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.please_feedback),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.rtb.rating <= 4.0) {
                listener?.send()
            } else {
                listener?.rating()
            }
        }
        binding.btnLater.setOnClickListener {
            dismiss()
            listener?.later()
        }
    }

    fun showDialog() {
        show()
    }
}