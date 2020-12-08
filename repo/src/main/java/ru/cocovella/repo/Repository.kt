package ru.cocovella.repo

interface Repository<T> {
    suspend fun getData(word: String): T
}
