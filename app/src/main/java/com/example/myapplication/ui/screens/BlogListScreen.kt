package com.example.myapplication.ui.screens

import android.text.Html
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.BlogViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel, contentPadding: PaddingValues) {
    val blogs by viewModel.blogs.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(Color(0xFF03182D)) //B.G.C
    ) {
        items(blogs) { blog ->
            BlogItem(blog.title.rendered, blog.link) { url ->
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString()) // ✅ Encode URL
                Log.d("BlogNavigation", "Navigating to URL: $encodedUrl")
                navController.navigate("blogDetail/$encodedUrl") // ✅ Pass blog URL on click
            }
        }
    }
}

@Composable
fun BlogItem(title: String, url: String, onClick: (String) -> Unit) {
    Text(
        text = Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT).toString(),
        color = Color.White,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onClick(url) }
    )
}
