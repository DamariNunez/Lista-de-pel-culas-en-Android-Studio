package com.example.movies

import com.squareup.moshi.Json

data class Movie (
    val id: Int,
    val name: String,
    @Json(name = "release")
    val release: String,
    @Json(name = "playtime")
    val playtime: String,
    val description: String,
    @Json(name = "poster")
    val imageUrl: String
) {
    fun asStoredMovie(): StoredMovie {
        return StoredMovie.newBuilder()
            .setId(id)
            .setName(name)
            .setRelease(release)
            .setPlaytime(playtime)
            .setDescription(description)
            .setImageUrl(imageUrl)
            .build()
    }
}