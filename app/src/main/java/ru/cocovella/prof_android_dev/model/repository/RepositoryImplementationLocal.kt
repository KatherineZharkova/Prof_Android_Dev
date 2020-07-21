package ru.cocovella.prof_android_dev.model.repository

import ru.cocovella.prof_android_dev.model.data.AppState
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
