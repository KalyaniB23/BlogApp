package com.example.myapplication

data class BlogPost(
    val id: Int,
    val title: Rendered,
    val content: Rendered,
    val link: String
)

data class Rendered(val rendered: String)