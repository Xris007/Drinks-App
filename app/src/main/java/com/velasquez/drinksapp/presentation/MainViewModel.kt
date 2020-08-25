package com.velasquez.drinksapp.presentation

import androidx.lifecycle.*
import com.velasquez.drinksapp.domain.repository.DrinksRepository
import com.velasquez.drinksapp.domain.vo.OperationResult
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val drinksRepository: DrinksRepository): ViewModel(){

    private val drinksData = MutableLiveData<String>()

    fun setDrink(drinkName: String){
        drinksData.value = drinkName
    }

    init {
        setDrink("Margarita")
    }

    val fetchDrinksList = drinksData.distinctUntilChanged().switchMap {drinkName ->
        liveData(Dispatchers.IO){
            emit(OperationResult.Loading())
            try {
                emit(drinksRepository.getDrinksList(drinkName))
            } catch (e: Exception){
                emit(OperationResult.Error(e))
            }
        }
    }

}