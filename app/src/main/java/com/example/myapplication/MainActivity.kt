package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.navigation.AppNavigation
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.BlogViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Step 1: Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/") // ✅ Replace with actual API URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // ✅ Step 2: Create API Service
        val apiService = retrofit.create(BlogApiService::class.java)

        // ✅ Step 3: Create Repository
        val blogRepository = BlogRepository(apiService)

        // ✅ Step 4: Create ViewModel using Factory
        val blogViewModel = ViewModelProvider(this, BlogViewModelFactory(blogRepository))
            .get(BlogViewModel::class.java)

        // ✅ Step 5: Set Content
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(blogViewModel, innerPadding)
                }
            }
        }
    }
}
