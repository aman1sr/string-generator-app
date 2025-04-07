package com.aman.randomstringgenerator.data

import com.aman.randomstringgenerator.data.database.RandomStringDao
import com.aman.randomstringgenerator.data.local.RandomStringEntity
import com.aman.randomstringgenerator.helper.ContentProviderHelper
import com.aman.randomstringgenerator.util.toEntity

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RandomStringRepository @Inject constructor(
    private val contentProviderHelper: ContentProviderHelper,
    private val dao: RandomStringDao
) {

    fun getStrings(): Flow<List<RandomStringEntity>> = dao.getAllStrings()

    suspend fun fetchAndSaveRandomString(length: Int) {         // todo: handle properly the thrown Error
        val model = contentProviderHelper.fetchStringFromProvider(length)
        val entity = model.toEntity()
        dao.insert(entity)
    }

    suspend fun deleteStringById(id: Int) = dao.deleteById(id)

    suspend fun deleteAll() = dao.deleteAll()
}