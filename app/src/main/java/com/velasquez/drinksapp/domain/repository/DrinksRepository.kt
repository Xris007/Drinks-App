package com.velasquez.drinksapp.domain.repository

import com.velasquez.drinksapp.data.model.Drink
import com.velasquez.drinksapp.domain.vo.OperationResult

interface DrinksRepository {
    suspend fun getDrinksList(drinkName: String): OperationResult<List<Drink>>
}