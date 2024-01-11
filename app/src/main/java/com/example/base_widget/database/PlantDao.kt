package com.example.base_widget.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base_widget.model.PlantModel

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(plant: PlantModel)

    @Query("SELECT id FROM plant")
    fun getAllIds(): ArrayList<Int>

    @Query("UPDATE plant SET name = :newName WHERE id = :plantId")
    fun updatePlantName(plantId: Int, newName: String)

    @Query("SELECT * FROM plant")
    fun getAllPlant(): ArrayList<PlantModel>

    @Query("DELETE FROM plant WHERE id = :plantId")
    fun deletePlant(plantId: Int)
}