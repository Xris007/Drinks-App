package com.velasquez.drinksapp.domain.repository

import com.velasquez.drinksapp.data.DataSource
import com.velasquez.drinksapp.data.model.Drink
import com.velasquez.drinksapp.domain.vo.OperationResult

class DrinksRepositoryImpl(private val dataSource: DataSource): DrinksRepository {
    override suspend fun getDrinksList(drinkName: String): OperationResult<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }
}