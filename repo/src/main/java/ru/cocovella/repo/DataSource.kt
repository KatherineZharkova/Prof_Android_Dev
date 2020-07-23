package ru.cocovella.repo

interface DataSource<T> {
    suspend fun getData(word: String): T
}
