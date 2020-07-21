package ru.cocovella.prof_android_dev.model.datasource

import ru.cocovella.prof_android_dev.model.data.AppState
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.room.HistoryDao
import ru.cocovella.prof_android_dev.utils.convertDataModelSuccessToEntity
import ru.cocovella.prof_android_dev.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
