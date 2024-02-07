package com.example.myapplication.utils

sealed class ResultOf<out T> {

    data class Success<out R>(val value: R) : ResultOf<R>()

    data class Failure(
        val message: String?
    ) : ResultOf<Nothing>()

    class Loading<out T> : ResultOf<T>()
}