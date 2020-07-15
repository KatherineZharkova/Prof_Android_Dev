package ru.cocovella.prof_android_dev.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}
