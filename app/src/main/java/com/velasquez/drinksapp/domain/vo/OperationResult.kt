package com.velasquez.drinksapp.domain.vo

import java.lang.Exception

sealed class OperationResult <out T> {
    class Loading<out T>: OperationResult<T>()
    data class Data<out T>(val data: T) : OperationResult<T>()
    data class Error<out T>(val exception: Exception) : OperationResult<T>()
}