package com.aman.randomstringgenerator.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.aman.randomstringgenerator.data.local.RandomStringEntity


@Database(entities = [RandomStringEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun randomStringDao(): RandomStringDao
}