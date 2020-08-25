package com.velasquez.drinksapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.velasquez.drinksapp.domain.repository.DrinksRepository

class VMFactory(private val repository: DrinksRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DrinksRepository::class.java).newInstance(repository)
    }
}