package com.example.feature_edit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.model_todo.TodoRepo
import com.example.model_todo.local.TodoDatabase
import kotlinx.coroutines.launch

class EditViewModel(app: Application) : AndroidViewModel(app) {
    private val todoRepo by lazy {
        TodoRepo(TodoDatabase.getDatabase(app, viewModelScope).todoDao())
    }

    fun getTodo(todoId: Int) {
        viewModelScope.launch {
            todoRepo.getTodo(todoId)
        }
    }

    fun updateTodo(todoId: Int, title: String, content: String) {
        viewModelScope.launch {
            todoRepo.updateTodo(todoId, title, content)
        }
    }

}