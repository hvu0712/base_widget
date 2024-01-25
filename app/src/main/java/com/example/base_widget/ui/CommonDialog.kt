package com.example.base_widget.ui

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
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
    }

    interface Listener {
        fun confirm()

        fun close()
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    fun getEditTextValue(): String {
        return when (isConfirmDialog) {
            true -> {
                cBinding.tvTitle.text.toString()
            }
            else -> {
                rBinding.editName.text.toString()
            }
        }
    }


    fun setUpDialog(iconResId: Int, name: String) {
        when(isConfirmDialog) {
            true -> {
                cBinding.ivSelected.setImageResource(iconResId)
            }

            else -> {
                rBinding.editName.setText(name)
                rBinding.ivSelected.setImageResource(iconResId)
            }
        }
    }

    private fun onclick() {
        when(isConfirmDialog)
        {
            true -> {
                cBinding.btConfirm.setOnClickListener {
                   listener?.confirm()
                }
                cBinding.btClose.setOnClickListener {
                    listener?.close()
                }
            }

            else -> {
                rBinding.btConfirm.setOnClickListener {
                    listener?.confirm()
                }
                rBinding.btClose.setOnClickListener {
                    listener?.close()
                }
            }
        }
    }

    fun showDialog() {
        show()
    }
}