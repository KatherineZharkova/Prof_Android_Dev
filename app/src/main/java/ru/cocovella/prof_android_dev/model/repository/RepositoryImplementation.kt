package ru.cocovella.prof_android_dev.model.repository

import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) : Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)
}
