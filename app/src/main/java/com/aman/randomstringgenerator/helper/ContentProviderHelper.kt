package com.aman.randomstringgenerator.helper

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.core.database.getStringOrNull
import com.aman.randomstringgenerator.data.model.RandomStringModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class ContentProviderHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val uri: Uri = Uri.parse("content://com.iav.contestdataprovider/text")

    suspend fun fetchStringFromProvider(length: Int): RandomStringModel {
        val resolver = context.contentResolver
        val cursor = resolver.query(
            uri,
            null,
            Bundle().apply {
                putInt("android:query-arg-limit", length)
            },
            null
        ) ?: throw IOException("No response from provider")

        cursor.use {
            if (it.moveToFirst()) {
                val json = it.getStringOrNull(it.getColumnIndex("data"))
                    ?: throw IOException("Empty data field")
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val adapter = moshi.adapter(RandomStringModel::class.java)
                return adapter.fromJson(json) ?: throw IOException("JSON parsing failed")
            } else {
                throw IOException("Cursor empty")
            }
        }
    }
}