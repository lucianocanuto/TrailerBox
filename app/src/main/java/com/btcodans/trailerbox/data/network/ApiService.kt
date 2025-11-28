package com.btcodans.trailerbox.data.network

import com.btcodans.trailerbox.data.models.MovieResponse
import com.btcodans.trailerbox.data.models.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): VideoResponse
}
