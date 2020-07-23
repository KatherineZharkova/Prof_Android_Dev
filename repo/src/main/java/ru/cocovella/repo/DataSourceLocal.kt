package ru.cocovella.repo

import ru.cocovella.repo.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}
