package com.example.imagesearchapp.extension

import androidx.lifecycle.MutableLiveData

/**
 * LiveData를 ArrayList처럼 운용할 수 있게 도와주는 Extension
 *
 */
fun <T> MutableLiveData<MutableList<T>>.clear() {
    value?.let { list ->
        list.clear()
        value = list
    }
}

fun <T> MutableLiveData<MutableList<T>>.isNotEmpty(): Boolean {
    return isEmpty().not()
}

fun <T> MutableLiveData<MutableList<T>>.isEmpty(): Boolean {
    return value?.isEmpty() ?: true
}

fun <T> MutableLiveData<MutableList<T>>.addAll(list: List<T>) {
    val newList = value ?: mutableListOf()
    newList.addAll(list)
    value = newList
}

fun <T> MutableLiveData<MutableList<T>>.add(t: T) {
    val newList = value ?: mutableListOf()
    newList.add(t)
    value = newList
}

fun <T> MutableLiveData<MutableList<T>>.size(): Int {
    return value?.size ?: 0
}