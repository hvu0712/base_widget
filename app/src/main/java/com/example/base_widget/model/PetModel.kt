package com.example.base_widget.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pet")
data class PetModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "image")
    var image: Int,
    @ColumnInfo(name = "level")
    var level: String,
    @ColumnInfo(name = "experience")
    var experience: Int,
): Serializable
