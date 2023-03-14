package com.example.youtubeapi.network

import com.example.youtubeapi.model.YoutubeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("search")
    fun getAllData(
        @Query("key") key: String = "AIzaSyAZ3ALbe4vzKb2UQgb1gnlEyATyKdgAC0Y",
//        @Query("channelId") channelId: String = "UCYLAirIEMMXtWOECuZAtjqQ",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: String = "30",
        @Query("part") part: String = "snippet,id"
    ): Call<YoutubeModel>
}