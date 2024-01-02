package com.example.base_widget.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_widget")
data class WidgetModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "list_path")
    var listPath: MutableList<String>,
    @ColumnInfo(name = "time_created")
    var timeCreated: Long,
    @ColumnInfo(name = "size")
    var size: String,
)