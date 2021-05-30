package dev.danilo.maders.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.danilo.maders.model.Portion

@Dao
interface PortionDAO {
    @Query("SELECT * FROM portion where id = :id")
    fun getById(id: Long) : Portion?

    @Query("SELECT * FROM portion")
    fun findAll(): List<Portion>

    @Insert
    fun insert(disciplina: Portion)

    @Delete
    fun delete(disciplina: Portion)

}