package com.aman.randomstringgenerator.util

import com.aman.randomstringgenerator.data.local.RandomStringEntity
import com.aman.randomstringgenerator.data.model.RandomStringModel

fun RandomStringModel.toEntity(): RandomStringEntity {
    return RandomStringEntity(
        value = this.randomText.value,
        length = this.randomText.length,
        created = this.randomText.created
    )
}