package ru.cocovella.repo

import ru.cocovella.repo.model.data.AppState
import ru.cocovella.repo.model.data.dto.SearchResultDto
import ru.cocovella.repo.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
