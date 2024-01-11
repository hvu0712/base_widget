package com.example.base_widget.ui

import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.widget.Toast
import com.example.base_widget.R
import com.example.base_widget.base.BaseActivity
import com.example.base_widget.base.LanguageScreenActivity
import com.example.base_widget.common.SharePrefUtils
import com.example.base_widget.common.hide
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.databinding.ActivitySettingsBinding
import com.example.base_widget.ui.dialog.RatingDialog
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task

class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

    private var lastClickTime: Long = 0
    private var ratingDialog: RatingDialog? = null

    override fun inflateViewBinding() = ActivitySettingsBinding.inflate(layoutInflater)

    override fun initView() {
        ratingDialog = RatingDialog(this)
        initRateDialog()
    }

    override fun setUpListener() {
        binding.apply {
            btnBack.setOnSingleClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            llLanguage.setOnSingleClickListener {
                showActivity(LanguageScreenActivity::class.java, null)
            }
            llRate.setOnSingleClickListener {
                ratingDialog?.showDialog()
            }
            llShare.setOnSingleClickListener {
                shareApp()
            }
            llPrivacy.setOnSingleClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://lg.taurusplay.store/privacy-policy")))
            }
        }
    }

    private fun shareApp() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val shareMessage =
            "${getString(R.string.app_name)} \n https://play.google.com/store/apps/details?id=${packageName}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//        AppOpenManager.getInstance().disableAppResumeWithActivity(HomeActivity::class.java)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
    }

    private fun initRateDialog() {
        ratingDialog?.setCancelable(true)
        ratingDialog?.init(this, object : RatingDialog.OnPress {
            override fun send() {
                ratingDialog?.dismiss()
                Toast.makeText(
                    applicationContext,
                    getString(R.string.rate_thanks),
                    Toast.LENGTH_SHORT
                ).show()
                SharePrefUtils.isRated = true
                binding.llRate.hide()
            }

            override fun rating() {
                rateInApp()
            }

            override fun cancel() {
                ratingDialog?.dismiss()
            }

            override fun later() {
                ratingDialog?.dismiss()
            }

            override fun dismissDialog() {

            }
        })
    }

    private fun rateInApp() {
        val manager: ReviewManager = ReviewManagerFactory.create(this)
        val request: Task<ReviewInfo> = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo: ReviewInfo = task.result
                val flow: Task<Void> = manager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener {
                    SharePrefUtils.isRated = true
                    ratingDialog?.dismiss()
                    binding.llRate.hide()
                }
            }
        }
    }
}