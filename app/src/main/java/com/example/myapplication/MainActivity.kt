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

        //Retrofit..
        val retrofit = Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(BlogApiService::class.java)

        val blogRepository = BlogRepository(apiService)

        val blogViewModel = ViewModelProvider(this, BlogViewModelFactory(blogRepository))
            .get(BlogViewModel::class.java)

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(blogViewModel, innerPadding)
                }
            }
        }
    }
}
