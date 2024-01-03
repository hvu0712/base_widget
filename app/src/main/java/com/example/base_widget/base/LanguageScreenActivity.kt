package com.example.base_widget.base

import android.content.Intent
import android.view.View
import com.colorwidgets.ios.widget.base.BaseActivity
import com.example.base_widget.MainActivity
import com.example.base_widget.R
import com.example.base_widget.common.RemoteConfig
import com.example.base_widget.common.SharePrefUtils
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.databinding.ActivityLanguageBinding

class LanguageScreenActivity : BaseActivity<ActivityLanguageBinding>() {
    companion object {
        const val LANGUAGE_FIRST_OPEN = "language_first_open"
    }

    private val languageAdapter = LanguageAdapter()
    override fun inflateViewBinding() = ActivityLanguageBinding.inflate(layoutInflater)

    override fun initView() {
        val isFromSplash = intent?.getBooleanExtra(LANGUAGE_FIRST_OPEN, false) ?: false
        binding.ivTick.setOnSingleClickListener {
            SharePrefUtils.language = languageAdapter.selectedLang
            if (isFromSplash) {
                SharePrefUtils.isShowLanguage = false
                if (SharePrefUtils.isShowIntro || RemoteConfig.isShowIntro) {
//                    startActivity(Intent(this, IntroActivity::class.java))
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            finish()
        }
        binding.ivBack.setOnSingleClickListener {
            finish()
        }
        binding.rvLanguage.adapter = languageAdapter.apply {
            setOnItemClickListener { _, _, position ->
                selectedLang = getItem(position).code
            }
            setNewInstance(getListLanguage())
        }

        if (isFromSplash) {
            binding.ivBack.visibility = View.GONE
        } else {
            binding.ivBack.visibility = View.VISIBLE
        }
    }

    private fun getListLanguage(): MutableList<ItemLanguage> {
        return mutableListOf(
            ItemLanguage(R.drawable.ic_english, "English", "en"),
            ItemLanguage(R.drawable.ic_portugal, "Portuguese", "pt"),
            ItemLanguage(R.drawable.ic_france, "French", "fr"),
            ItemLanguage(R.drawable.ic_spanish, "Spanish", "es"),
            ItemLanguage(R.drawable.ic_india, "Hindi", "hi"),
        )
    }
}