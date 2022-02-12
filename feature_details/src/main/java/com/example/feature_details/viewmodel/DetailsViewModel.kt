package com.example.feature_details.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.model_todo.TodoRepo
import com.example.model_todo.local.TodoDatabase
import kotlinx.coroutines.launch

class DetailsViewModel (app: Application) : AndroidViewModel(app) {

    private val todoRepo by lazy {
        TodoRepo(TodoDatabase.getDatabase(app, viewModelScope).todoDao())
    }

    fun checkCompleted(id: Int, checked: Boolean) {
        viewModelScope.launch {
            todoRepo.setComplete(id, checked)
        }
    }
}