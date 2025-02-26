package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BlogDetailScreen(blogUrl: String) {
    val decodedUrl = URLDecoder.decode(blogUrl, StandardCharsets.UTF_8.toString()) // ✅ Decode URL

    if (decodedUrl.isBlank()) {
        Text(text = "Error: Blog URL is missing", modifier = Modifier.fillMaxSize())
    } else {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true // ✅ Enable JavaScript
                    settings.domStorageEnabled = true // ✅ Enable local storage
                    webViewClient = WebViewClient() // ✅ Open links inside WebView
                    loadUrl(decodedUrl) // ✅ Load the blog URL
                }
            }
        )
    }
}
