package ru.cocovella.prof_android_dev.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}
