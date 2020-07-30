package ru.cocovella.repo

import ru.cocovella.repo.model.data.AppState
import ru.cocovella.repo.model.data.dto.SearchResultDto

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
