package ru.cocovella.repo

import ru.cocovella.repo.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}
