package com.aman.randomstringgenerator.data.model

data class RandomText(
    val value: String,
    val length: Int,
    val created: String
)

data class RandomStringModel(val randomText: RandomText)