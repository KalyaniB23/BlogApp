package com.example.myapplication

class BlogRepository(private val api: BlogApiService) {
    suspend fun fetchBlogs(): List<BlogPost> = api.getBlogPosts()
}