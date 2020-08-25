package com.velasquez.drinksapp.data

import com.velasquez.drinksapp.data.model.Drink
import com.velasquez.drinksapp.domain.vo.OperationResult
import com.velasquez.drinksapp.domain.vo.RetrofitClient

class DataSource {

    suspend fun getDrinkByName(drinkName: String): OperationResult<List<Drink>>{
        return OperationResult.Data(RetrofitClient.webService.getDrinkByName(drinkName).drinkList)
    }


}