package com.btcodans.trailerbox.data.repository

import com.btcodans.trailerbox.data.models.VideoResponse
import com.btcodans.trailerbox.data.network.ApiService
import com.btcodans.trailerbox.data.network.RetrofitClient

class MovieRepository (private val apiKey: String) {
    private val api = RetrofitClient.instance.create(ApiService::class.java)


    suspend fun getPopular() = api.getPopular(apiKey)
    suspend fun getNowPlaying() = api.getNowPlaying(apiKey)
    suspend fun getUpcoming() = api.getUpcoming(apiKey)
    suspend fun getVideos(movieId: Int): VideoResponse = api.getVideos(movieId, apiKey)
}