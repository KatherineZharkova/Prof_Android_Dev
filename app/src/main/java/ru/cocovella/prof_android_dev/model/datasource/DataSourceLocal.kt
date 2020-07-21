package ru.cocovella.prof_android_dev.model.datasource

import ru.cocovella.prof_android_dev.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}
