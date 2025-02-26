package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel(private val repository: BlogRepository) : ViewModel() {
    private val _blogs = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogs: StateFlow<List<BlogPost>> = _blogs

    init {
        viewModelScope.launch {
            _blogs.value = repository.fetchBlogs()
        }
    }
}

// ✅ Factory to manually create BlogViewModel
class BlogViewModelFactory(private val repository: BlogRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BlogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}