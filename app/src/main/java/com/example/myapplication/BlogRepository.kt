package com.example.myapplication

import android.util.Log
import com.example.myapplication.BlogApiService
import com.example.myapplication.BlogPost
import retrofit2.HttpException
import java.io.IOException

class BlogRepository(private val api: BlogApiService) {
    suspend fun fetchBlogs(): List<BlogPost> {
        return try {
            val response = api.getBlogPosts()
            if (response.isSuccessful && response.body() != null) {
                Log.d("API Response", "Received ${response.body()!!.size} blogs")
                response.body()!!
            } else {
                Log.e("API Error", "Failed to load blogs, Response: ${response.errorBody()}")
                emptyList()
            }
        } catch (e: IOException) {
            Log.e("Network Error", "Check Internet Connection", e)
            emptyList()
        } catch (e: HttpException) {
            Log.e("Server Error", "HTTP Error ${e.code()}", e)
            emptyList()
        }
    }
}
