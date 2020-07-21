package ru.cocovella.prof_android_dev.model.repository

import ru.cocovella.prof_android_dev.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}
