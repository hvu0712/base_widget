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

    @Query("UPDATE pet SET name = :newName WHERE id = :petId")
    fun updatePetName(petId: Int, newName: String)

    @Query("SELECT * FROM pet")
    fun getAllPet(): MutableList<PetModel>

    @Query("DELETE FROM pet WHERE id = :petId")
    fun deletePet(petId: Int)
}