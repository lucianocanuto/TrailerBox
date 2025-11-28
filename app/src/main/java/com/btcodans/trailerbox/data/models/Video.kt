package com.btcodans.trailerbox.data.models

data class Video(
    val id: String,
    val key: String, // this is the YouTube key
    val name: String,
    val site: String,
    val type: String
)
