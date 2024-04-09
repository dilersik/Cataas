package com.example.catass.model

data class CatItem(
    val _id: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>
)