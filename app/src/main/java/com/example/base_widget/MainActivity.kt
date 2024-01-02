package com.example.base_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.colorwidgets.ios.widget.base.BaseActivity
import com.example.base_widget.common.setOnSingleClickListener
import com.example.base_widget.databinding.ActivityMainBinding
import com.example.base_widget.utils.LargeWidget
import com.example.base_widget.utils.MediumWidget
import com.example.base_widget.utils.SmallWidget
import com.example.base_widget.utils.WidgetSize

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        binding.apply {
            tvSmall.setOnSingleClickListener {
                createSmallWidget(1, WidgetSize.SIZE_S)
            }
            tvSmall1.setOnSingleClickListener {
                createSmallWidget(2, WidgetSize.SIZE_S)
            }
            tvMedium.setOnSingleClickListener {
                createMediumWidget(1, WidgetSize.SIZE_M)
            }
            tvMedium1.setOnSingleClickListener {
                createMediumWidget(2, WidgetSize.SIZE_M)
            }
            tvLarge.setOnSingleClickListener {
                createMediumWidget(1, WidgetSize.SIZE_L)
            }
            tvLarge1.setOnSingleClickListener {
                createMediumWidget(2, WidgetSize.SIZE_L)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createSmallWidget(type: Int, size: WidgetSize) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val clazz = when (size) {
            WidgetSize.SIZE_S -> SmallWidget::class.java
            WidgetSize.SIZE_M -> MediumWidget::class.java
            WidgetSize.SIZE_L -> LargeWidget::class.java
        }
        val provider = ComponentName(this, clazz)
        if (appWidgetManager.isRequestPinAppWidgetSupported) {
            val intent = Intent(this, clazz)
            intent.putExtra("data", true)
            intent.putExtra("type", type)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            appWidgetManager.requestPinAppWidget(provider, null, pendingIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMediumWidget(type: Int, size: WidgetSize) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val clazz = when (size) {
            WidgetSize.SIZE_S -> SmallWidget::class.java
            WidgetSize.SIZE_M -> MediumWidget::class.java
            WidgetSize.SIZE_L -> LargeWidget::class.java
        }
        val provider = ComponentName(this, clazz)
        if (appWidgetManager.isRequestPinAppWidgetSupported) {
            val intent = Intent(this, clazz)
            intent.putExtra("data", true)
            intent.putExtra("type", type)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            appWidgetManager.requestPinAppWidget(provider, null, pendingIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createLargeWidget(type: Int, size: WidgetSize) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val clazz = when (size) {
            WidgetSize.SIZE_S -> SmallWidget::class.java
            WidgetSize.SIZE_M -> MediumWidget::class.java
            WidgetSize.SIZE_L -> LargeWidget::class.java
        }
        val provider = ComponentName(this, clazz)
        if (appWidgetManager.isRequestPinAppWidgetSupported) {
            val intent = Intent(this, clazz)
            intent.putExtra("data", true)
            intent.putExtra("type", type)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            appWidgetManager.requestPinAppWidget(provider, null, pendingIntent)
        }
    }
}