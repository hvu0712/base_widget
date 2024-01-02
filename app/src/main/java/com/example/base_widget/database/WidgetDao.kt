package com.example.base_widget.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base_widget.model.WidgetModel

@Dao
interface WidgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWidget(photoWidget: WidgetModel)

    @Query("SELECT * from photo_widget WHERE id = :id")
    fun getWidgetById(id: Int): WidgetModel?

    @Query("SELECT id FROM photo_widget")
    fun getAllIds(): MutableList<Int>

    @Query("SELECT * FROM photo_widget")
    fun getAllWidget(): MutableList<WidgetModel>
}