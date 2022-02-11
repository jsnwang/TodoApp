package com.example.feature_details.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.model_todo.TodoRepo
import com.example.model_todo.local.TodoDatabase

class DetailsViewModel (app: Application) : AndroidViewModel(app) {

    private val todoRepo by lazy {
        TodoRepo(TodoDatabase.getDatabase(app, viewModelScope).todoDao())
    }

    fun showDetails(){

    }

    fun checkCompleted(complete:Boolean){

    }
}