package com.example.base_widget.utils

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.base_widget.R
import com.example.base_widget.database.AppDatabase
import com.example.base_widget.model.WidgetModel

class SmallWidget : AppWidgetProvider() {

    private var type = 0

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getIntExtra("type", 0)
        this.type = type
        val appWidgetManager = AppWidgetManager.getInstance(context)
        // id cua tat ca widget
        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, SmallWidget::class.java))
        if (type != 0 ){
            if (appWidgetIds.isNotEmpty()) {
                // id cua tat ca widget da duoc save db
                val allId = AppDatabase.getInstance(context).widgetDao().getAllIds()
                appWidgetIds.sortDescending()
                val appWidgetId = appWidgetIds.first()
                if (allId.contains(appWidgetId)) return
                val widget = WidgetModel(
                    id = appWidgetId,
                    mutableListOf(),
                    timeCreated = System.currentTimeMillis(),
                    size = "S"
                )
                AppDatabase.getInstance(context).widgetDao().insertWidget(widget)
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val layoutResId = getWidgetLayoutResId()
        val views = getWidgetRemoteViews(context, layoutResId)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getWidgetRemoteViews(
        context: Context,
        layoutResId: Int,
    ): RemoteViews {
        return RemoteViews(context.packageName, layoutResId)
    }

    private fun getWidgetLayoutResId(): Int {
        return when (type) {
            1 -> R.layout.small_widget_layout
            2 -> R.layout.small_widget_layout_1
            else -> {
                R.layout.preview_layout_s
            }
        }
    }
}