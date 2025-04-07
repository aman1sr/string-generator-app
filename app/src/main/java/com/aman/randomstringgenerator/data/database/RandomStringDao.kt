package com.aman.randomstringgenerator.data.database

import androidx.room.*
import com.aman.randomstringgenerator.data.local.RandomStringEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomStringDao {
    @Query("SELECT * FROM random_strings ORDER BY created DESC")
    fun getAllStrings(): Flow<List<RandomStringEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(randomString: RandomStringEntity)

    @Query("DELETE FROM random_strings WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM random_strings")
    suspend fun deleteAll()
}