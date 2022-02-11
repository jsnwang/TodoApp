package com.example.feature_details.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.model_todo.TodoRepo
import com.example.model_todo.local.TodoDatabase
import kotlinx.coroutines.launch

class DetailsViewModel (app: Application) : AndroidViewModel(app) {

    private val todoRepo by lazy {
        TodoRepo(TodoDatabase.getDatabase(app, viewModelScope).todoDao())
    }

    fun showDetails() {

    }

    fun checkCompleted(id: Int, checked: Boolean) {
        viewModelScope.launch {
//            val todo = todoRepo.getTodo(id)
//            //todo.isComplete = checked
            todoRepo.setComplete(id, checked)
//            Log.d("Todo", todo.toString())
//
//
        }
    }
}