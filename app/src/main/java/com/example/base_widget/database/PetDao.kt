package com.example.base_widget.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base_widget.model.PetModel

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPet(pet: PetModel)

    @Query("SELECT id FROM pet")
    fun getAllIds(): MutableList<Int>

    @Query("SELECT id FROM pet WHERE name = :name")
    fun getId(name: String): Int

    @Query("UPDATE pet SET name = :newName WHERE id = :petId")
    fun updatePetName(petId: Int, newName: String)

    @Query("UPDATE pet SET level = :newLevel WHERE id = :petId")
    fun updatePetLevel(petId: Int, newLevel: String)

    @Query("UPDATE pet SET experience = :newExperience WHERE id = :petId")
    fun updatePetExperience(petId: Int, newExperience: Int)

    @Query("SELECT * FROM pet")
    fun getAllPet(): MutableList<PetModel>

    @Query("DELETE FROM pet WHERE id = :petId")
    fun deletePet(petId: Int)
}