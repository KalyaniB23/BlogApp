package com.example.myapplication.ui.screens

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.BlogViewModel

@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel, contentPadding: PaddingValues) {
    val blogs by viewModel.blogs.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
        items(blogs) { blog ->
            BlogItem(blog.title.rendered, blog.link) { url ->
                navController.navigate("blogDetail/$url")
            }
        }
    }
}

@Composable
fun BlogItem(title: String, url: String, onClick: (String) -> Unit) {
    Text(
        text = Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT).toString(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(url) }
    )
}
