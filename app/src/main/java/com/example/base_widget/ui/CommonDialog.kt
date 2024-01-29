package com.example.base_widget.ui

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.base_widget.R
import com.example.base_widget.databinding.DialogConfirmBinding
import com.example.base_widget.databinding.DialogDeleteBinding
import com.example.base_widget.databinding.DialogRenameBinding

class CommonDialog(private val context: Context,private val type: Int) : Dialog(
    context, R.style.BaseDialog
) {
    private val cBinding = DialogConfirmBinding.inflate(layoutInflater)
    private val rBinding = DialogRenameBinding.inflate(layoutInflater)
    private val dBinding = DialogDeleteBinding.inflate(layoutInflater)


    private var listener: Listener? = null

    init {
        when (type) {
            0 -> setContentView(cBinding.root)
            1 -> setContentView(rBinding.root)
            else -> setContentView(dBinding.root)
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
        return when (type) {
            0 -> {
                cBinding.tvTitle.text.toString()
            }
            1 -> {
                rBinding.editName.text.toString()
            }
            else -> {
                dBinding.editName.text.toString()
            }
        }
    }


    fun setUpDialog(iconResId: Int, name: String) {
        when(type) {
            0 -> {
                cBinding.ivSelected.setImageResource(iconResId)
            }

            1 -> {
                rBinding.editName.setText(name)
                rBinding.ivSelected.setImageResource(iconResId)
            }

            else -> {
//                rBinding.editName.setText(name)
//                rBinding.ivSelected.setImageResource(iconResId)
            }
        }
    }

    private fun onclick() {
        when(type)
        {
            0 -> {
                cBinding.btConfirm.setOnClickListener {
                   listener?.confirm()
                }
                cBinding.btClose.setOnClickListener {
                    listener?.close()
                }
            }

            1 -> {
                rBinding.btConfirm.setOnClickListener {
                    listener?.confirm()
                }
                rBinding.btClose.setOnClickListener {
                    listener?.close()
                }
            }

            2 -> {
                dBinding.btConfirm.setOnClickListener {
                    listener?.confirm()
                }
                dBinding.btClose.setOnClickListener {
                    listener?.close()
                }
                dBinding.btCancel.setOnClickListener {
                    listener?.close()
                }
            }
        }
    }

    fun showDialog() {
        show()
    }
}