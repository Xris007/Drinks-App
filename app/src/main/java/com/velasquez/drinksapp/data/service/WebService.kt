package com.velasquez.drinksapp.data.service

import com.velasquez.drinksapp.data.model.Drink
import com.velasquez.drinksapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getDrinkByName(@Query("s") drinkName: String): DrinkList
}